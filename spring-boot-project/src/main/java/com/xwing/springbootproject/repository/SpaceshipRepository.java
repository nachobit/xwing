package com.xwing.springbootproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xwing.springbootproject.model.Spaceship;

@Repository
public interface SpaceshipRepository extends JpaRepository<Spaceship, Long> {
	
	Page<Spaceship> findByNameContaining(String name, Pageable pageable);

}
