package com.deepak.tools.creator;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogOutputCreatorController {
	private static final Logger logger = LoggerFactory.getLogger(LogOutputCreatorController.class);
	

    private static final String template = "Number of lines printed to std out: %s!";
    private static final String LINE = String.join("", Collections.nCopies(80, "X"));
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/loglines")
    public LogCount greeting(@RequestParam(value="kiloLines", defaultValue="10") int numKiloLines) {
    	counter.incrementAndGet();
    	logger.info("Got a request with kiloLines {}, number {}", numKiloLines, counter.get());
    	int numLines = numKiloLines * 1000;
    	for (int i = 0; i< numLines; i++) {
    	    logger.info("{}", LINE);
    	}
        return new LogCount(counter.get(),
                            String.format(template, numLines));
    }
}
