package com.xwing.springbootproject.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.xwing.springbootproject.model.SpaceshipType;

@Component
public class MessageConsumer {
	
	@RabbitListener(queues = "spaceships_queue")
	public void receiveMessage(String spaceshipType) {
		System.out.println("Received message Spaceship type: " + spaceshipType);
		//Validate Spaceship
		if (isValidSpaceship(spaceshipType)) {
			 System.out.println("Valid spaceship type received: " + spaceshipType);
		} else {
			 System.out.println("Invalid spaceship type received: " + spaceshipType);
		}
	}
	
	private boolean isValidSpaceship(String spaceshipType) {
		for (SpaceshipType validTypes : SpaceshipType.values()) {
			if (validTypes.name().equals(spaceshipType)) {
				return true;
			}
		}
		return false;
	}
}
