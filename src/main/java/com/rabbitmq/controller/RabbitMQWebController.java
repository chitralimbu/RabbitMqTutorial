package com.rabbitmq.controller;

import com.rabbitmq.domain.Employee;
import com.rabbitmq.service.RabbitMqSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQWebController {

    private final RabbitMqSender rabbitMqSender;

    public RabbitMQWebController(RabbitMqSender rabbitMqSender) {
        this.rabbitMqSender = rabbitMqSender;
    }

    @GetMapping("/producer/{empName}/{empId}")
    public String producer(@PathVariable("empName") String empName, @PathVariable("empId") String empId){
        Employee employee = Employee.builder()
                .empName(empName)
                .empId(empId)
                .build();

        rabbitMqSender.send(employee);
        return "Message sent to the RabbitMQ chitra Successfully";
    }
}
