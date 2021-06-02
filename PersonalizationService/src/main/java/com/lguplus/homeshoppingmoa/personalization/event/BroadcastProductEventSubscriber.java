package com.lguplus.homeshoppingmoa.personalization.event;

import com.lguplus.homeshoppingmoa.common.event.CustomEvent;
import com.lguplus.homeshoppingmoa.personalization.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

@Slf4j
@SuppressWarnings("deprecation")
@EnableBinding(CustomProcess.class)
@RequiredArgsConstructor
public class BroadcastProductEventSubscriber {

    private final ReservationService reservationService;

    @StreamListener(value = CustomProcess.INPUT_BROADCAST_PRODUCT, condition = "headers['event-type']=='delete'")
    public void onDeletedEvent(@Payload CustomEvent<List<Long>> payload) {
        log.debug("> kafka event sub, type: {}, event(List<Long>) size: {}", payload.getType(), payload.getEvent().size());

        reservationService.pushAlarmToTargets(payload.getEvent());
    }
}
