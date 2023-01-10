package com.erdemnayin.edaorderservice.service;

import com.erdemnayin.edabasedomains.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderProducer {

    private final NewTopic newTopic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    public OrderProducer(NewTopic newTopic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent orderEvent){
        logger.info(String.format("Order event -> %s", orderEvent.toString()));

        String topicName = newTopic.name();
        String messageKey = UUID.randomUUID().toString();

//        Message<OrderEvent> message = MessageBuilder
//                .withPayload(orderEvent)
//                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
//                .build();

        kafkaTemplate.send(topicName, messageKey, orderEvent);
    }
}
