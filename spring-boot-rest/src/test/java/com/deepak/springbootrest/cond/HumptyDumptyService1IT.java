package com.deepak.springbootrest.cond;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ConditionalTestConfig.class)
public class HumptyDumptyService1IT {

    @Autowired private HumptyDumptyService humptyDumptyService;

    @Test
    public void reciteTest() {
        String msg = humptyDumptyService.recite();

        assertThat(msg, containsString("sat"));
    }
}
