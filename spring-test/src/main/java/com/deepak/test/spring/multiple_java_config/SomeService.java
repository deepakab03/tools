package com.deepak.test.spring.multiple_java_config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deepak.test.spring.common.Greeting;

public class SomeService {
    private static final Logger logger = LoggerFactory.getLogger(SomeService.class);
    
    private Greeting greting;

    public SomeService(Greeting greting) {
        super();
        this.greting = greting;
    }
    
    public void greet() {
        logger.info("Greeting: {}", greting);
    }
}
