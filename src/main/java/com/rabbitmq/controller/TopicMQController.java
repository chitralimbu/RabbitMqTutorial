package com.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topic")
public class TopicMQController {

    private final AmqpTemplate amqpTemplate;

    public TopicMQController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @GetMapping("/producer/{exchange}/{routing}/{data}")
    public String topicProducer(@PathVariable("exchange") String exchange, @PathVariable("routing") String routing, @PathVariable("data") String data){
         amqpTemplate.convertAndSend(exchange, routing, data);
         return "Message sent to RabbitMQ Topic Exchange successfully";
    }
}
