package com.rabbitmq.service;

import com.rabbitmq.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMqDLQConsumer {

    @RabbitListener(queues = "my.queue")
    public void receivedMessage(User user){
        log.info("Received message user: " + user);
        if(user.getSalary() > 1000){
            throw new IllegalArgumentException("Salary exceeds 1000");
        }
    }

}
