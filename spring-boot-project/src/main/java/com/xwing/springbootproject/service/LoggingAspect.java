package com.xwing.springbootproject.service;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
    private final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.xwing.springbootproject.service.SpaceshipService.getSpaceshipById(..)) && args(id)")
    public void logNegativeId(Long id) {
        if (id < 0) {
        	LOGGER.warn("Requested spaceship with negative id: {}", id);
        }
    }

}
