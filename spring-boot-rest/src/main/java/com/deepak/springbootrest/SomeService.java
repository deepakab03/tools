package com.deepak.springbootrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
