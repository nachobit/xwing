package com.xwing.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xwing.springbootproject.exception.NotFoundException;
import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.repository.SpaceshipRepository;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {
	
	@Autowired
	private SpaceshipRepository spaceshipRepository;

//	@GetMapping
//	public List<Spaceship> getAllSpaceships() {
//		return spaceshipRepository.findAll();
//	}
	
	@GetMapping
	public Page<Spaceship> getAllSpaceships(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(required = false) String name) {
		Pageable pageable = PageRequest.of(page, size);
		if (name != null && !name.isEmpty()) {
			return spaceshipRepository.findByNameContainingIgnoreCase(name, pageable);
		} else {
			return spaceshipRepository.findAll(pageable);
		}
	}

	
	public Spaceship getSpaceshipById(@PathVariable Long id) {
		return spaceshipRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Spaceship not found with id " + id));
	}
	
    @PostMapping
    public Spaceship createSpaceship(@RequestBody Spaceship spaceship) {
        return spaceshipRepository.save(spaceship);
    }
    
    @PutMapping("/{id}")
    public Spaceship updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceship) {
        Spaceship existingSpaceship = spaceshipRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Spaceship not found with id " + id));
        existingSpaceship.setName(spaceship.getName());
        existingSpaceship.setDescription(spaceship.getDescription());
        existingSpaceship.setType(spaceship.getType());
        return spaceshipRepository.save(existingSpaceship);
    }

    @DeleteMapping("/{id}")
    public void deleteSpaceship(@PathVariable Long id) {
    	spaceshipRepository.deleteById(id);
    }
}
