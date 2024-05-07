package com.xwing.springbootproject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.repository.SpaceshipRepository;
import com.xwing.springbootproject.service.SpaceshipService;

public class SpaceshipServiceTest {

	@Mock
    private SpaceshipRepository spaceshipRepository;

    @InjectMocks
    private SpaceshipService spaceshipService;

    @Test
    public void testGetAllSpaceships() {
    	List<Spaceship> spaceshipList = new ArrayList<>();
    	spaceshipList.add(new Spaceship("Falcon 9"));
    	spaceshipList.add(new Spaceship("Apollo 11"));
    	Page<Spaceship> page = new PageImpl<>(spaceshipList);

    	
    	// Call repository
    	when(spaceshipRepository.findAll(any(Pageable.class))).thenReturn(page);
//        when(spaceshipRepository.findAll()).thenReturn(Arrays.asList(new Spaceship("Falcon"), new Spaceship("X-Wing")));

        // Call method
    	Page<Spaceship> result = spaceshipService.getAllSpaceships(Pageable.unpaged());

        // Verify results
    	assertEquals(spaceshipList.size(), result.getContent().size());
        assertEquals("Falcon 9", result.getContent().get(0).getName());

    }

}
