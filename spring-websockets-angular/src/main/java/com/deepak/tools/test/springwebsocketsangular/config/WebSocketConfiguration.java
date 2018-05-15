package com.deepak.tools.test.springwebsocketsangular.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.deepak.tools.test.springwebsocketsangular.controller.SomeClass;
import com.deepak.tools.test.springwebsocketsangular.controller.WebSocketController;

@Configuration
@EnableWebSocketMessageBroker
@Import({SessionConfig.class, SecurityConfig.class})
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
   
    @Inject private SimpMessagingTemplate messagingTemplate;
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/socket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app")
                .enableSimpleBroker("/uiUpdate");
    }
    
    @Bean public WebSocketController webSocketController() {
        return new WebSocketController(messagingTemplate, new SomeClass());
    }
}
    