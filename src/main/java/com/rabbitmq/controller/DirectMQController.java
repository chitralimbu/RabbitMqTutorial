package com.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direct")
public class DirectMQController {

    private final AmqpTemplate amqpTemplate;

    public DirectMQController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @GetMapping("/producer/{exchange}/{routing-key}/{data}")
    public String directProducer(@PathVariable("exchange") String exchange, @PathVariable("data") String data, @PathVariable("routing-key") String routingKey){
        amqpTemplate.convertAndSend(exchange,routingKey,data);
        return "Message sent to RabbitMQ successfully";
    }
}
