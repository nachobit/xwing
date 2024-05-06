package com.xwing.springbootproject;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.repository.SpaceshipRepository;
import com.xwing.springbootproject.service.SpaceshipService;

@RunWith(MockitoJUnitRunner.class)
public class SpaceshipServiceTest {

	@Mock
    private SpaceshipRepository spaceshipRepository;

    @InjectMocks
    private SpaceshipService spaceshipService;

    @Test
    public void testGetAllSpaceships() {
    	// Simulate repository
        when(spaceshipRepository.findAll()).thenReturn(Arrays.asList(new Spaceship("Falcon"), new Spaceship("X-Wing")));

        // Call method
        List<Spaceship> spacecrafts = spaceshipService.getAllSpaceships();

        // Verify results
        assertEquals(2, spacecrafts.size());
        assertEquals("Falcon", spacecrafts.get(0).getName());
        assertEquals("X-Wing", spacecrafts.get(1).getName());
    }

}
