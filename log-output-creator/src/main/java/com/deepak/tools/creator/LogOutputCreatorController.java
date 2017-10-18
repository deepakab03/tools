package com.deepak.tools.creator;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
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
    public LogCount logToOutput(@RequestParam(value = "numLines", defaultValue = "10") int numLinesPassedIn) {
        return logToStdOut(numLinesPassedIn);
    }

    @RequestMapping("/out/loglines")
    public LogCount logToStdOut(@RequestParam(value = "numLines", defaultValue = "10") int numLinesPassedIn) {
        int numLines = validateAndPrintLog(numLinesPassedIn);
        for (int i = 0; i < numLines; i++) {
            logger.info("{}", LINE);
        }
        return logCounter(numLines);
    }

    private int validateAndPrintLog(int numLinesPassedIn) {
        Assert.isTrue(numLinesPassedIn > 0, numLinesPassedIn + " has to be greater than 0");
        counter.incrementAndGet();
        logger.info("Got a request with numLines {}, number {}", numLinesPassedIn, counter.get());
        return numLinesPassedIn;
    }

    private LogCount logCounter(int numLines) {
        return new LogCount(counter.get(), String.format(template, numLines));
    }

    @RequestMapping("/out/loglines/debug")
    public LogCount logToStdOutAtDebug(@RequestParam(value = "numLines", defaultValue = "10") int numLinesPassedIn) {
        int numLines = validateAndPrintLog(numLinesPassedIn);
        for (int i = 0; i < numLines; i++) {
            logger.debug("{}", LINE);
        }
        return logCounter(numLines);
    }

    @RequestMapping("/out/printlines")
    public LogCount printoStdOut(@RequestParam(value = "numLines", defaultValue = "10") int numLinesPassedIn) {
        int numLines = validateAndPrintLog(numLinesPassedIn);
        for (int i = 0; i < numLines; i++) {
            System.out.println(LINE);
        }
        return logCounter(numLines);
    }

    @RequestMapping("/err/printlines")
    public LogCount printToStdErr(@RequestParam(value = "numLines", defaultValue = "10") int numLinesPassedIn) {
        int numLines = validateAndPrintLog(numLinesPassedIn);
        for (int i = 0; i < numLines; i++) {
            System.err.println(LINE);
        }
        return logCounter(numLines);
    }
}
