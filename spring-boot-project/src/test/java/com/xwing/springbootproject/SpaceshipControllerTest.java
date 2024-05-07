package com.xwing.springbootproject;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.xwing.springbootproject.controller.SpaceshipController;
import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.service.SpaceshipService;

@RunWith(SpringRunner.class)
@WebMvcTest(SpaceshipController.class)
public class SpaceshipControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SpaceshipService spaceshipService;

	@Test
	public void testGetAllSpaceships() throws Exception {
		// Simulate repository
		when(spaceshipService.getAllSpaceships()).thenReturn(Arrays.asList(new Spaceship("Falcon"), new Spaceship("X-Wing")));

		// HTTP GET Request
		mockMvc.perform(get("/api/spaceships"))
		// Verify 200 Status OK
		.andExpect(status().isOk())
		// Verify JSON expected
		.andExpect(content().json("[{'name': 'Falcon'}, {'name': 'X-Wing'}]"));
	}

}
