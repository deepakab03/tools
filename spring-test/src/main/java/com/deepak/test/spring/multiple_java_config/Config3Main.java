package com.deepak.test.spring.multiple_java_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.AbstractApplicationContext;

@Configuration
@Import({Config1.class, Config2.class})
public class Config3Main {

    @Autowired private Config1 config1;
    
    @Bean public SomeService someService() {
        return new SomeService(config1.greeting());
    }
    
    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Config3Main.class);
        
        SomeService someService = ctx.getBean(SomeService.class);
        
        someService.greet();//should give Config2 class's value
    }
}
