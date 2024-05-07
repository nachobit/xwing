package com.xwing.springbootproject.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.xwing.springbootproject.controller.SpaceshipController;
import com.xwing.springbootproject.model.Spaceship;
import com.xwing.springbootproject.service.SpaceshipService;

@WebMvcTest(SpaceshipController.class)
@AutoConfigureMockMvc
public class SpaceshipIntegrationTest {
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpaceshipService spaceshipService;
	
	@Test
    public void testGetAllSpaceships() throws Exception {
        Page<Spaceship> spaceships = new PageImpl<>(Collections.emptyList());
        when(spaceshipService.getAllSpaceships(any(Pageable.class))).thenReturn(spaceships);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/spaceships"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
	
	@Test
    public void testGetSpaceshipById() throws Exception {
        Spaceship spaceship = new Spaceship("X-Wing");
        when(spaceshipService.getSpaceshipById(1L)).thenReturn(spaceship);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/spaceships/1"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
	
	

}
