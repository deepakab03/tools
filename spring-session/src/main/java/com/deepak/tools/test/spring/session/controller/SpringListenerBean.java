package com.deepak.tools.test.spring.session.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.session.events.SessionCreatedEvent;
import org.springframework.session.events.SessionDestroyedEvent;

public class SpringListenerBean  {
    private static final Logger logger = LoggerFactory.getLogger(SpringListenerBean.class);

   @EventListener
   public void sessionCreated(SessionCreatedEvent sessionCreatedEvent) {
       logger.info("SPRING SESSION CREATED!!");
   }

   @EventListener
   public void sessionDestroyedEvent(SessionDestroyedEvent sessionDestroyedEvent) {
       logger.info("SPRING SESSION DESTROYED!!");
   }

}
