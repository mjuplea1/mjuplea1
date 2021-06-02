package com.lguplus.homeshoppingmoa.personalization.event;

import com.lguplus.homeshoppingmoa.common.event.CustomEvent;
import com.lguplus.homeshoppingmoa.common.model.dto.broadcastproduct.MainProductDto;
import com.lguplus.homeshoppingmoa.personalization.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

@Slf4j
@SuppressWarnings("deprecation")
@EnableBinding(CustomSink.class)
@RequiredArgsConstructor
public class BroadcastProductEventSubscriber {

    private final ReservationService reservationService;

    @StreamListener(value = CustomSink.INPUT_BROADCAST_PRODUCT, condition = "headers['event-type']=='delete'")
    public void onDeletedEvent(@Payload CustomEvent<List<Long>> payload) {
        log.debug("> kafka event sub, type: {}, event(List<Long>) size: {}", payload.getType(), payload.getEvent().size());

        reservationService.pushAlarmToTargets(payload.getEvent());
    }

    @StreamListener(value = CustomSink.INPUT_BROADCAST_PRODUCT, condition = "headers['event-type']=='insert_update'")
    public void onInsertedAndUpdatedEvent(@Payload CustomEvent<List<MainProductDto>> payload) {
        /**
         * 해당 메소드는 CustomEvent event-type이 insert_update인 경우를 무시하기 위해 정의.
         * 정의하지 않을 경우 insert_update 조건으로 메시지가 들어올 때마다 WARNING LOG가 찍힘.
         */
    }
}
