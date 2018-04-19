package com.deepak.tools.test.springwebsocketsangular;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.deepak.tools.test.springwebsocketsangular.model.Greeting;

@Controller
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    private int counter;
    
    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        super();
        this.messagingTemplate = messagingTemplate;
    }
    
    @MessageMapping("/send/message")
    public void onReceivedMessage(String message) {
        this.messagingTemplate.convertAndSend("/chat", new Date() + " : " + message);
        this.messagingTemplate.convertAndSend("/chat", new Greeting(counter++, "he yaa"));
    }
}
