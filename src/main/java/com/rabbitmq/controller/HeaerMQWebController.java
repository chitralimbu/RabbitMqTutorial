package com.rabbitmq.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/header")
public class HeaerMQWebController {

    private final AmqpTemplate amqpTemplate;

    public HeaerMQWebController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @GetMapping("/producer/{exchange}/{department}/{data}")
    public String headerProducer(@PathVariable("exchange") String exchange, @PathVariable("department") String department, @PathVariable("data") String message){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("department", department);
        MessageConverter messageConverter = new SimpleMessageConverter();
        Message amqpMessage = messageConverter.toMessage(message, messageProperties);
        amqpTemplate.send(exchange, "", amqpMessage);
        return "Header message successfully sent";
    }
}
