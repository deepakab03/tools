package com.deepak.springbootrest.cond;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HumptyDumptyServiceImpl1 implements HumptyDumptyService {
    private static final Logger logger = LoggerFactory.getLogger(HumptyDumptyServiceImpl1.class);

    @Override
    public String recite() {
        String msg = "humpty dumpty sat";
        logger.info(msg);

        return msg;
    }

}
