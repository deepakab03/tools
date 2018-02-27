package com.deepak.test.spring.multiple_java_config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deepak.test.spring.common.Greeting;

@Configuration
public class Config2 {

    @Bean
    public Greeting greeting() {
        return new Greeting(200, "overriden");
    }
}
