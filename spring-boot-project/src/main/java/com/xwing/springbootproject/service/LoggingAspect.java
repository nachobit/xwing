package com.xwing.springbootproject.service;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

//    @After("execution(* com.xwing.springbootproject.controller.SpaceshipController.getSpaceshipById(..)) && args(id) && args(id)")
//    public void logNegativeIdRequest(JoinPoint joinPoint, Long id) {
//        if (id < 0) {
//            logger.info("Requested spaceship with negative id: {}", id);
//        }
//    

    @Before("execution(* com.xwing.springbootproject.service.SpaceshipService.getSpaceshipById(..)) && args(id)")
    public void logNegativeId(Long id) {
        if (id < 0) {
            logger.warn("Requested spaceship with negative id: {}", id);
        }
    }

}
