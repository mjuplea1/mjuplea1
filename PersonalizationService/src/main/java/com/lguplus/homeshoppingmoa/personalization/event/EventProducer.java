package com.lguplus.homeshoppingmoa.personalization.event;

import com.lguplus.homeshoppingmoa.common.event.CustomEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@SuppressWarnings("deprecation")
@RequiredArgsConstructor
@EnableBinding(CustomSource.class)
@Component
public class EventProducer {

    private final CustomSource process;

    @SendTo(CustomSource.OUTPUT_MESSAGE_REQUEST)
    public void publishSMSEvent(CustomEvent<?> event) {
        log.info("> kafka event pub: {}", event.getType());
        this.process.outputMessageRequest().send(
                MessageBuilder.withPayload(event).setHeader("event-type", event.getType()).build()
        );
    }

    @SendTo(CustomSource.OUTPUT_MESSAGE_REQUEST)
    public void publishMMSEvent(CustomEvent<?> event) {
        log.info("> kafka event pub: {}", event.getType());
        this.process.outputMessageRequest().send(
                MessageBuilder.withPayload(event).setHeader("event-type", event.getType()).build()
        );
    }

    @SendTo(CustomSource.OUTPUT_PUSH_REQUEST)
    public void publishPushEvent(CustomEvent<?> event) {
        log.info("> kafka event pub: {}", event.getType());
        this.process.outputPushRequest().send(
                MessageBuilder.withPayload(event).setHeader("event-type", event.getType()).build()
        );
    }
}
