package com.xwing.springbootproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.xwing.springbootproject.model.Spaceship;

public interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {
	
	Page<Spaceship> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
