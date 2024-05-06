package com.xwing.springbootproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.repository.SpaceshipRepository;

public class SpaceshipService {
	
	@Autowired
    private SpaceshipRepository spaceshipRepository;
	
	@Cacheable("spaceships")
    public List<Spaceship> getAllSpaceships() {
		return spaceshipRepository.findAll();
	}

}
