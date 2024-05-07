package com.xwing.springbootproject;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.xwing.springbootproject.controller.SpaceshipController;
import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.service.SpaceshipService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SpaceshipController.class)
@AutoConfigureMockMvc
public class SpaceshipControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Mock
	private SpaceshipService spaceshipService;
	
	@InjectMocks
	private SpaceshipController spaceshipController;

	@Test
	public void testGetAllSpaceships() throws Exception {
		// Test data
		List<Spaceship> spaceshipList = new ArrayList<>();
		spaceshipList.add(new Spaceship("Falcon 9"));
		spaceshipList.add(new Spaceship("Apollo 11"));
		Page<Spaceship> page = new PageImpl<>(spaceshipList);

		// Simulate repository
		when(spaceshipService.getAllSpaceships(any(Pageable.class))).thenReturn(page);

		// HTTP GET Request
		mockMvc.perform(get("/api/spaceships"))
		// Verify 200 Status OK
		.andExpect(status().isOk())
		// Verify JSON expected
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.content.length()").value(spaceshipList.size()));
	}

}
