package com.deepak.tools.test.spring.session.config;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import com.deepak.tools.test.spring.session.controller.SpringListenerBean;
import com.deepak.tools.test.spring.session.util.CustomMapSessionRepo;
import com.deepak.tools.test.spring.session.controller.SomeRestController;

@Configuration
@EnableSpringHttpSession
public class SessionConfig {

    @Autowired private ApplicationEventPublisher applicationEventPublisher;

    @Bean
    public SomeRestController someRestController() {
        return new SomeRestController();
    }

    @Bean
    public SpringListenerBean springListenerBean() {
        return new SpringListenerBean();
    }

    //actual session stuff if not enabled via application.properties
//    @Bean
//    public MapSessionRepository sessionRepository() {
//        return new MapSessionRepository(new ConcurrentHashMap<>());
//    }

    @Bean
    public MapSessionRepository sessionRepository() {
        CustomMapSessionRepo customMapSessionRepo = new CustomMapSessionRepo(new ConcurrentHashMap<>(), applicationEventPublisher);
        customMapSessionRepo.setDefaultMaxInactiveInterval(30);//test
        return customMapSessionRepo;
    }

}
