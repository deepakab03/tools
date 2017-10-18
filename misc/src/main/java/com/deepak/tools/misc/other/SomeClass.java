package com.deepak.tools.misc.other;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeClass {
    private static final Logger logger = LoggerFactory.getLogger(SomeClass.class);
    
    private static final int DEF_Y = 20;
    private static final int DEF_X = 10;

    public static void main(String[] args) throws AWTException, InterruptedException {
        Robot robot = new Robot();

        int x1=DEF_X,y1=DEF_Y;
        logger.info("Doing the test");
        Random random = new Random();
        for (int i = 0; i < 10 * 1000; i++) {
            robot.mouseMove(x1++, y1++);
            if ( i % 10 == 0) {
                x1 = DEF_X + random.nextInt(400); 
                y1 = DEF_Y + random.nextInt(400);
            }
            
            
            int sleepTimeInSec = random.nextInt(21);
            logger.info("Sleeping for {} secs in iteration {}", sleepTimeInSec, i);
            Thread.sleep(sleepTimeInSec * 1000);
        }
    }

}
