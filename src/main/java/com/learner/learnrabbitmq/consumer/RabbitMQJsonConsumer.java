package com.learner.learnrabbitmq.consumer;

import com.learner.learnrabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue_json.name}"})
    public void consumeJsonMessage(User user){
        LOGGER.info(String.format("Message Received -> %s", user.toString()));
    }

}
