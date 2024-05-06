package com.xwing.springbootproject.model;

import jakarta.persistence.*;

@Entity
public class Spaceship {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Enumerated(EnumType.STRING)
    private SpaceshipType type;

    @Column(length = 1000)
    private String description;
    
	public Spaceship() {}
	
	public Spaceship(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SpaceshipType getType() {
		return type;
	}

	public void setType(SpaceshipType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
