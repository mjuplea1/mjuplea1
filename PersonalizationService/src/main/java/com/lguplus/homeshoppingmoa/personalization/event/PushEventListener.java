package com.lguplus.homeshoppingmoa.personalization.event;

import com.lguplus.homeshoppingmoa.common.event.CustomEvent;
import com.lguplus.homeshoppingmoa.common.model.dto.personalization.PushDto;
import com.lguplus.homeshoppingmoa.personalization.service.RsvAlarmHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PushEventListener {

    private final EventProducer eventProducer;
    private final String PUSH_EVENT_TYPE = "push";
    private final RsvAlarmHistoryService rsvAlarmHistoryService;

    public void onPushEvent(CustomEvent<PushDto> event) {
        event.setType(PUSH_EVENT_TYPE);
        this.eventProducer.publishPushEvent(event);
        rsvAlarmHistoryService.save(event.getEvent());
    }
}
