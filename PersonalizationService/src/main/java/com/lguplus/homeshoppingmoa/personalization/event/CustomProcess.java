package com.lguplus.homeshoppingmoa.personalization.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

@SuppressWarnings("deprecation")
public interface CustomProcess {
    String INPUT_BROADCAST_PRODUCT = "input-broadcast-product";

    String OUTPUT_MESSAGE_REQUEST = "output_message_request";
    String OUTPUT_PUSH_REQUEST = "output_push_request";

    /**
     * @return input-broadcast-product channel.
     */
    @Input(CustomProcess.INPUT_BROADCAST_PRODUCT)
    SubscribableChannel inputBroadcastProduct();

    @Output(CustomProcess.OUTPUT_MESSAGE_REQUEST)
    MessageChannel outputMessageRequest();

    @Output(CustomProcess.OUTPUT_PUSH_REQUEST)
    MessageChannel outputPushRequest();
}
