package com.xwing.springbootproject.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.repository.SpaceshipRepository;
import com.xwing.springbootproject.service.impl.SpaceshipServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SpaceshipServiceTest {

	@Mock
    private SpaceshipRepository spaceshipRepository;

    @InjectMocks
    private SpaceshipServiceImpl spaceshipService;

    @Test
    public void testGetAllSpaceships() {
    	List<Spaceship> spaceshipList = new ArrayList<>();
    	spaceshipList.add(new Spaceship("Halcon M"));
    	spaceshipList.add(new Spaceship("Apollo 11"));
    	Page<Spaceship> page = new PageImpl<>(spaceshipList);
    	
    	// Call Repository
    	when(spaceshipRepository.findAll(any(Pageable.class))).thenReturn(page);

        // Call method
    	Page<Spaceship> result = spaceshipService.getAllSpaceships(Pageable.unpaged());

        // Assert
    	assertEquals(spaceshipList.size(), result.getContent().size());
        assertEquals("Halcon M", result.getContent().get(0).getName());

    }
    
    @Test
    public void testGetSpaceshipById() {
        Spaceship spaceship = new Spaceship("Apolo 11");
        
        // Call Repository
        when(spaceshipRepository.findById(1L)).thenReturn(Optional.of(spaceship));

        // Call method
        Spaceship result = spaceshipService.getSpaceshipById(1L);

        // Assert
        assertEquals("Apolo 11", result.getName());
    }
    
    @Test
    public void testSearchSpaceshipsByName() {
        List<Spaceship> spaceshipList = new ArrayList<>();
        spaceshipList.add(new Spaceship("Halcon M"));
    	spaceshipList.add(new Spaceship("X-Wing"));
        Page<Spaceship> page = new PageImpl<>(spaceshipList);
        
        // Call Repository
        when(spaceshipRepository.findByNameContainingIgnoreCase("Wing", Pageable.unpaged())).thenReturn(page);

        // Call method
        Page<Spaceship> result = spaceshipService.searchSpaceshipsByName("Wing", Pageable.unpaged());

        // Assert
        assertEquals(2, result.getTotalElements());
        assertEquals("Halcon M", result.getContent().get(0).getName());
        assertEquals("X-Wing", result.getContent().get(1).getName());
    }
    
    @Test
    public void testCreateSpaceship() {
        Spaceship newSpaceship = new Spaceship();
        newSpaceship.setName("X-Wing");
        
        // Call Repository
        when(spaceshipRepository.save(newSpaceship)).thenReturn(newSpaceship);

        // Call method
        Spaceship result = spaceshipService.createSpaceship(newSpaceship);

        // Assert
        verify(spaceshipRepository, times(1)).save(newSpaceship);
        assertEquals("X-Wing", result.getName());
    }
    
    @Test
    public void testUpdateSpaceship() {
        Spaceship existingSpaceship = new Spaceship("X-Wing");
        Spaceship updatedSpaceship = new Spaceship("X-Wing v2");

        // Call Repository
        when(spaceshipRepository.findById(1L)).thenReturn(Optional.of(existingSpaceship));
        when(spaceshipRepository.save(existingSpaceship)).thenReturn(updatedSpaceship);

        // Call method
        Spaceship result = spaceshipService.updateSpaceship(1L, updatedSpaceship);

        // Assert
        verify(spaceshipRepository, times(1)).findById(1L);
        verify(spaceshipRepository, times(1)).save(existingSpaceship);
        assertEquals("X-Wing v2", result.getName());
    }
    
    @Test
    public void testDeleteSpaceship() {
        long spaceshipId = 1L;

        // Call method
        spaceshipService.deleteSpaceship(spaceshipId);

        // Assert
        verify(spaceshipRepository, times(1)).deleteById(spaceshipId);
    }

}
