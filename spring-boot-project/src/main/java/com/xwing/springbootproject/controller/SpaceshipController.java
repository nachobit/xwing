package com.xwing.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.service.SpaceshipService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/spaceships")
public class SpaceshipController {
	
	@Autowired
	private SpaceshipService spaceshipService;

	@Operation(summary = "Get all spaceships", description = "Get all spaceships with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Spaceships not found")
    })
    @GetMapping
    public ResponseEntity<Page<Spaceship>> getAllSpaceships(Pageable pageable) {
        Page<Spaceship> spaceships = spaceshipService.getAllSpaceships(pageable);
        return ResponseEntity.ok().body(spaceships);
    }
	
	// Method to obtain a spaceship by its id
    @GetMapping("/{id}")
    public ResponseEntity<Spaceship> getSpaceshipById(@PathVariable Long id) {
    	Spaceship spaceship = spaceshipService.getSpaceshipById(id);
        return ResponseEntity.ok().body(spaceship);
    }

    // Method to search for spaceships by name
    @GetMapping("/search")
    public ResponseEntity<Page<Spaceship>> searchSpaceshipsByName(@RequestParam String nameParam, Pageable pageable) {
        Page<Spaceship> spaceships = spaceshipService.searchSpaceshipsByName(nameParam, pageable);
        return ResponseEntity.ok().body(spaceships);
    }
    
    // Method to create a new spaceship
    @PostMapping
    public ResponseEntity<Spaceship> createSpaceship(@RequestBody Spaceship spaceship) {
    	Spaceship createdSpaceship = spaceshipService.createSpaceship(spaceship);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpaceship);
    }
    
    // Method to upgrade an existing spaceship
    @PutMapping("/{id}")
    public ResponseEntity<Spaceship> updateSpaceship(@PathVariable Long id, @RequestBody Spaceship spaceship) {
    	Spaceship updatedSpaceship = spaceshipService.updateSpaceship(id, spaceship);
        return ResponseEntity.ok().body(updatedSpaceship);
    }
    
    // Method to delete a spaceship by its id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpaceship(@PathVariable Long id) {
    	spaceshipService.deleteSpaceship(id);
        return ResponseEntity.ok().build();
    }
    
}
