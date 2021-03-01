package com.rabbitmq.service;

import com.rabbitmq.domain.Employee;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "${chitra.rabbitmq.queue}")
    public void receivedMessage(Employee employee){
        System.out.println("Received Message from RabbitMQ: " + employee);
    }

    @RabbitListener(queues = "${chitra.rabbitmq.marketing}")
    public void receiveMarketingMessage(String message){
        System.out.println("Marketing message: " + message);
    }

    @RabbitListener(queues = "marketingFanoutQueue")
    public void receiveFanoutMarketing(String message){
        System.out.println("Marketing fanout message: " + message);
    }

    @RabbitListener(queues = "financeFanoutQueue")
    public void receiveFanoutFinance(String message){
        System.out.println("Finance fanout message: " + message);
    }

    @RabbitListener(queues = "adminFanoutQueue")
    public void receiveFanoutAdmin(String message){
        System.out.println("Admin fanout message: " + message);
    }
}
