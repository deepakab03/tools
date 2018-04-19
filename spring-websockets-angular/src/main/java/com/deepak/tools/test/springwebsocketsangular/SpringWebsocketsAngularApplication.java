package com.deepak.tools.test.springwebsocketsangular;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.deepak.tools.test.springwebsocketsangular.config.WebSocketConfiguration;

@SpringBootApplication
@Import(value= {WebSocketConfiguration.class})
public class SpringWebsocketsAngularApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebsocketsAngularApplication.class, args);
	}
}
