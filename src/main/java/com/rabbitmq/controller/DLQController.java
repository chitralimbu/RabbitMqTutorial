package com.rabbitmq.controller;

import com.rabbitmq.domain.Employee;
import com.rabbitmq.domain.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dlq")
public class DLQController {
    private final AmqpTemplate amqpTemplate;

    public DLQController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @GetMapping("/producer/{name}/{id}/{salary}")
    public String producer(@PathVariable("name") String name, @PathVariable("id") String id, @PathVariable("salary") int salary){
        User user = User.builder()
                .salary(salary)
                .userId(id)
                .username(name)
                .build();

        amqpTemplate.convertAndSend("myExchange", "myQueue", user);

        return "DLQ exchange tutorial message sent";
    }

}
