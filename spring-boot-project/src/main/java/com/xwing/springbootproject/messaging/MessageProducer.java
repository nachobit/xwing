package com.xwing.springbootproject.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xwing.springbootproject.model.Spaceship;

@Component
public class MessageProducer {
	 
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendMessage(Spaceship spaceship) {
		String spaceshipType = spaceship.getType().toString(); 
		System.out.println("Sending message with Spaceship Type: " + spaceshipType);
		rabbitTemplate.convertAndSend("spaceships_exchange", "", spaceshipType);
	}
}
