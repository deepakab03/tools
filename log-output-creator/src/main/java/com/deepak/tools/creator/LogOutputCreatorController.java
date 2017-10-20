package com.deepak.tools.creator;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogOutputCreatorController {
    private static final String DEFAULT_NUM_LINES = "1000";

//    private static final Logger logger = LoggerFactory.getLogger(LogOutputCreatorController.class);
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(LogOutputCreatorController.class.getName());

    private static final String template = "Number of lines printed to std out: %s!";
    private static final String LINE = String.join("", Collections.nCopies(80, "X"));
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/loglines")
    public LogCount logToOutput(@RequestParam(value = "numLines", defaultValue = DEFAULT_NUM_LINES) int numLinesPassedIn) {
        return logToStdOut(numLinesPassedIn, false);
    }

    @RequestMapping("/out/loglines")
    public LogCount logToStdOut(@RequestParam(value = "numLines", defaultValue = DEFAULT_NUM_LINES) int numLinesPassedIn,
            @RequestParam(value = "async", defaultValue = "false") boolean async) {
        return doOp(numLinesPassedIn, async, false);
    }

    private int validateAndPrintLog(int numLinesPassedIn) {
        Assert.isTrue(numLinesPassedIn > 0, numLinesPassedIn + " has to be greater than 0");
        counter.incrementAndGet();
//        logger.info("Got a request with numLines {}, number {}", numLinesPassedIn, counter.get());
        logger.info("Got a request with numLines " + numLinesPassedIn +" number " +  counter.get());
        return numLinesPassedIn;
    }

    private LogCount logCounter(int numLines) {
        return new LogCount(counter.get(), String.format(template, numLines));
    }

    @RequestMapping("/out/loglines/debug")
    public LogCount logToStdOutAtDebug(@RequestParam(value = "numLines", defaultValue = DEFAULT_NUM_LINES) int numLinesPassedIn) {
        int numLines = validateAndPrintLog(numLinesPassedIn);
        for (int i = 0; i < numLines; i++) {
//            logger.debug(LINE);
            logger.fine(LINE);
        }
        return logCounter(numLines);
    }

    @RequestMapping("/out/printlines")
    public LogCount printoStdOut(@RequestParam(value = "numLines", defaultValue = DEFAULT_NUM_LINES) int numLinesPassedIn,
            @RequestParam(value = "async", defaultValue = "false") boolean async) {
        return doOp(numLinesPassedIn, async, true);
    }

    private LogCount doOp(int numLinesPassedIn, boolean async, boolean directSysout) {
        int numLines = validateAndPrintLog(numLinesPassedIn);
        if (async) {
            new Thread(() -> {
                try {
                    int sleepTime = (int) Math.max(5, Math.random() * 10 + 3);
                    output(directSysout, "Sleeping for " + sleepTime + " seconds");
                    Thread.sleep(5 * 1000);
                    for (int i = 0; i < numLines; i++) {
                        output(directSysout, LINE);
                    }
                    System.out.println("Printing a test exception as well: ");
                    throw new InterruptedException("test test!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
//                    throw new RuntimeException("another test");
                }
            }).start();
        } else {
            for (int i = 0; i < numLines; i++) {
                output(directSysout, LINE);
            }
        }
        return logCounter(numLines);
    }
    
    private void output(boolean directSysout, String msg) { 
        if (directSysout) {
            System.out.println(msg);
        } else {
            logger.info(msg);
        }
    }

    @RequestMapping("/err/printlines")
    public LogCount printToStdErr(@RequestParam(value = "numLines", defaultValue = DEFAULT_NUM_LINES) int numLinesPassedIn) {
        int numLines = validateAndPrintLog(numLinesPassedIn);
        for (int i = 0; i < numLines; i++) {
            System.err.println(LINE);
        }
        return logCounter(numLines);
    }
}
