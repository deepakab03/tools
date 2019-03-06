package com.deepak.springbootrest.cond;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HumptyDumptyServiceImpl2 implements HumptyDumptyService {
    private static final Logger logger = LoggerFactory.getLogger(HumptyDumptyServiceImpl2.class);

    @Override
    public String recite() {
        String msg = "Humpty refused to sit";
        logger.info(msg);

        return msg;
    }

}
