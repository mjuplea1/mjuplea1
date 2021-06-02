package com.lguplus.homeshoppingmoa.personalization.event;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

@SuppressWarnings("deprecation")
public interface CustomSource {

    String OUTPUT_MESSAGE_REQUEST = "output_message_request";
    String OUTPUT_PUSH_REQUEST = "output_push_request";

    @Output(CustomSource.OUTPUT_MESSAGE_REQUEST)
    MessageChannel outputMessageRequest();

    @Output(CustomSource.OUTPUT_PUSH_REQUEST)
    MessageChannel outputPushRequest();
}
