package com.xwing.springbootproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xwing.springbootproject.exception.NotFoundException;
import com.xwing.springbootproject.messaging.MessageProducer;
import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.repository.SpaceshipRepository;
import com.xwing.springbootproject.service.SpaceshipService;

@Service
public class SpaceshipServiceImpl implements SpaceshipService {
	
	@Autowired
    private SpaceshipRepository spaceshipRepository;
	
	@Autowired
	private MessageProducer messageProducer;

	@Override
	@Cacheable("spaceships")
	public Page<Spaceship> getAllSpaceships(Pageable pageable) {
		return spaceshipRepository.findAll(pageable);
	}

	@Override
	public Spaceship getSpaceshipById(Long id) {
		return spaceshipRepository.findById(id).
				orElseThrow(() -> new NotFoundException("Spaceship not found with id: " + id));
	}

	@Override
	public Page<Spaceship> searchSpaceshipsByName(String name, Pageable pageable) {
		return spaceshipRepository.findByNameContainingIgnoreCase(name, pageable);
	}

	@Override
	public Spaceship createSpaceship(Spaceship spaceship) {
		Spaceship savedSpaceship = spaceshipRepository.save(spaceship);
		//messageProducer.sendMessage(savedSpaceship);
		return savedSpaceship;
	}

	@Override
	@CacheEvict(value = "spaceships", allEntries = true)
	public Spaceship updateSpaceship(Long id, Spaceship newSpaceship) {
		Spaceship spaceship = getSpaceshipById(id);
		spaceship.setName(newSpaceship.getName());
		return spaceshipRepository.save(spaceship);
	}

	@Override
	public void deleteSpaceship(Long id) {
		spaceshipRepository.deleteById(id);
	}
	
}
