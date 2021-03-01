package com.rabbitmq.service;

import com.rabbitmq.domain.Employee;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqSender {

    private final AmqpTemplate amqpTemplate;

    public RabbitMqSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @Value("${chitra.rabbitmq.exchange}")
    private String exchange;

    @Value("${chitra.rabbitmq.routingkey}")
    private String routingKey;

    public void send(Employee company){
        amqpTemplate.convertAndSend(exchange, routingKey, company);
        System.out.println("Send msg = " + company);
    }

}
