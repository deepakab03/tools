package com.deepak.springbootrest.cond;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ConditionalTestConfig.class)
@EnableAspectJAutoProxy(proxyTargetClass=true)
@TestPropertySource(properties="someProp=false")
public class HumptyDumptyService2IT {

    @Autowired private HumptyDumptyService service1;

    @Test
    public void reciteTest() {
        String msg = service1.recite();

        assertThat(msg, containsString("sit"));
    }
}
