package com.learner.learnrabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQPublisher.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("{rabbitmq.routing_key.name}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    public RabbitMQPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
