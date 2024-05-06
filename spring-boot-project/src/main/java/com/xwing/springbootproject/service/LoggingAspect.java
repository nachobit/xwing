package com.xwing.springbootproject.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("execution(* com.xwing.springbootproject.controller.SpaceshipController.getSpaceshipById(..)) && args(id) && args(id)")
    public void logNegativeIdRequest(JoinPoint joinPoint, Long id) {
        if (id < 0) {
            logger.info("Requested spacecraft with negative id: {}", id);
        }
    }

}
