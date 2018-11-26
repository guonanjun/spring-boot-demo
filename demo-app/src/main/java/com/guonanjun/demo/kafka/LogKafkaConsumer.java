package com.guonanjun.demo.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-21
 */
@Component
public class LogKafkaConsumer {

    private static final String TOPIC = "dev-log";

    @KafkaListener(topics = LogKafkaConsumer.TOPIC)
    public void processMessage(String message) {
        System.out.println("Received sample message [" + message + "]");
    }
}
