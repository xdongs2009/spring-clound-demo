package com.xds.orderservice.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

@EnableBinding(Source.class)
public class KafkaSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSender.class);

    @Autowired
    private Source source;

    public boolean sendMessage(String message) {
        try {
            LOGGER.info("start send message, value = " + message);
            source.output().send(MessageBuilder.withPayload(message).build());
        } catch (Exception e) {
            LOGGER.info("send message error,reason = " + e.getMessage());
            return false;
        }
        LOGGER.info("finish send message, value = " + message);
        return true;
    }
}
