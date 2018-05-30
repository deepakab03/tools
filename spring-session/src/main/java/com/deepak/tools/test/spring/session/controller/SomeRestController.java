package com.deepak.tools.test.spring.session.controller;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("someService")
@ResponseBody
public class SomeRestController {
    private static final Logger logger = LoggerFactory.getLogger(SomeRestController.class);

    private AtomicInteger counter = new AtomicInteger(0);

    @RequestMapping(method = RequestMethod.GET, value="sayHi")
    public String sayHello() {
        return "hello: " + counter.getAndIncrement();
    }

    @RequestMapping(method = RequestMethod.GET, value="saySessionHi")
    public String saySessionHello(HttpSession session) {
        Object counterObj = session.getAttribute("sessionCounter");
        logger.info("Existing Session counter attrib: {}", (Object) counterObj);
        if (counterObj == null) {
            counterObj = Integer.valueOf(0);
        } else {
            counterObj = Integer.valueOf(( (Integer) counterObj).intValue() + 1 );
        }
        session.setAttribute("sessionCounter",  counterObj);
        return "hello: " + counterObj;
    }

    @RequestMapping(method = RequestMethod.GET, value="closeSessionHi")
    public String closeSession(HttpSession session) {
        logger.info("Session counter attrib counter before close: {}", (Object) session.getAttribute("sessionCounter"));
        session.invalidate();
        return "Closed";
    }
}
