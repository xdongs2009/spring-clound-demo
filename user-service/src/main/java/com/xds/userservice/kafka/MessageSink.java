package com.xds.userservice.kafka;

import com.xds.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class MessageSink {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageSink.class);

    @Autowired
    private UserService userService;

    @StreamListener(Sink.INPUT)
    public void process(String message) {
        LOGGER.info("get message,content = " + message);
        String[] arrays = message.split("\"");
        int size = arrays.length;
        if (size == 0) {
            return;
        }
        Long orderId;
        try {
            orderId = Long.parseLong(arrays[size - 1]);
        } catch (Exception e) {
            LOGGER.info("error param");
            return;
        }
        long id = userService.saveEvent(orderId);
        LOGGER.info("[user-service]-save event success,id = " + id);
    }
}
