package com.lguplus.homeshoppingmoa.personalization.event;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

@SuppressWarnings("deprecation")
public interface CustomSink {
    String INPUT_BROADCAST_PRODUCT = "input-broadcast-product";

    @Input(CustomSink.INPUT_BROADCAST_PRODUCT)
    SubscribableChannel inputBroadcastProduct();
}
