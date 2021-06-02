package com.lguplus.homeshoppingmoa.personalization.event;

import com.lguplus.homeshoppingmoa.common.event.CustomEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageEventListener {

    private final EventProducer eventProducer;
    private final String SMS_EVENT_TYPE = "SMS";
    private final String MMS_EVENT_TYPE = "MMS";

    public void onSmsMessageEvent(CustomEvent<?> event) {
        event.setType(SMS_EVENT_TYPE);
        this.eventProducer.publishSMSEvent(event);
    }

    public void onMMSMessageEvent(CustomEvent<?> event) {
        event.setType(MMS_EVENT_TYPE);
        this.eventProducer.publishMMSEvent(event);
    }
}
