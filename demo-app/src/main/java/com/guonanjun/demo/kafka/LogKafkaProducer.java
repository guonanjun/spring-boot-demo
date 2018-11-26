package com.guonanjun.demo.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 描述:
 *
 * @author guonanjun
 * @date 2018-11-21
 */
@Component
public class LogKafkaProducer {

    private static final String TOPIC = "dev-log";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        this.kafkaTemplate.send(TOPIC, message);
    }

}
