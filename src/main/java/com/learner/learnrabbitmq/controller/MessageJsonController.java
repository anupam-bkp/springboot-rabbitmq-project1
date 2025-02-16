package com.learner.learnrabbitmq.controller;

import com.learner.learnrabbitmq.dto.User;
import com.learner.learnrabbitmq.publisher.RabbitMQJsonPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {

    private RabbitMQJsonPublisher rabbitMQJsonPublisher;

    public MessageJsonController(RabbitMQJsonPublisher rabbitMQJsonPublisher) {
        this.rabbitMQJsonPublisher = rabbitMQJsonPublisher;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user){
        rabbitMQJsonPublisher.sendMessage(user);
        return ResponseEntity.ok("Json message sent to RabbitMQ ...");
    }

}
