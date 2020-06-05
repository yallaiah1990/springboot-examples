package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/rabbitmq/")
public class RabbitMQController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@PostMapping(value = "/producer")
	public String producer(@RequestBody Employee emp) {
		rabbitMQSender.send(emp);
		return "Message sent to the RabbitMQ JavaInUse Successfully";
	}

}
