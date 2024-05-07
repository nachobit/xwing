package com.xwing.springbootproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xwing.springbootproject.exception.NotFoundException;
import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.repository.SpaceshipRepository;
import com.xwing.springbootproject.service.SpaceshipService;

public class SpaceshipServiceImpl implements SpaceshipService {
	
	@Autowired
    private SpaceshipRepository spaceshipRepository;
	
	@Cacheable("spaceships")
    public List<Spaceship> getAllSpaceships() {
		return spaceshipRepository.findAll();
	}
	

	@Override
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
		return spaceshipRepository.save(spaceship);
	}

	@Override
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
