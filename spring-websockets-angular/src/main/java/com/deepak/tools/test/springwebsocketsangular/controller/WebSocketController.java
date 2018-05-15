package com.deepak.tools.test.springwebsocketsangular.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.deepak.tools.test.springwebsocketsangular.model.Greeting;

@Controller
public class WebSocketController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    
    private final SimpMessagingTemplate messagingTemplate;

    private int counter;

    private SomeClass someClass;
    
    public WebSocketController(SimpMessagingTemplate messagingTemplate, SomeClass someClass) {
        super();
        this.messagingTemplate = messagingTemplate;
        this.someClass = someClass;
    }
    
    @MessageMapping("/send/blah")
    public void onBlah() {
        logger.info("blah!!");
    }
    
    
    @MessageMapping("/send/startSubscription")
    public boolean onReceivedMessage() {
        logger.info("got a message!");
        this.messagingTemplate.convertAndSend("/uiUpdate", new Date() + " : ");
        this.messagingTemplate.convertAndSend("/uiUpdate", new Greeting(counter++, "he yaa"));
        return true;
    }
    
    @MessageMapping("/send/stopSubscription")
    public boolean onUnSubscribe() {
        logger.info("STOPPING subscription on request");
        return true;
    }
}
