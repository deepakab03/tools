package com.deepak.springbootrest;

import java.net.ConnectException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

/**
 * Run with mvn spring-boot:run or java -jar target/<name of jar> Run with
 * http://localhost:8080/greeting
 *
 * @author abrahd2
 *
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired private Environment env;

    //required when the packaging is war and we want to deploy in an external web container
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }

    @PostConstruct
    public void init() throws InterruptedException {
        int sleepTimeSeconds = env.getProperty("sleepTimeSeconds", Integer.class, 20);
        logger.info("Sleeping for {} seconds from test app", sleepTimeSeconds);
        Thread.sleep(sleepTimeSeconds * 1000);
        if ("true".equals(env.getProperty("throwEx"))) {
            throw new RuntimeException("throwing on err");
        }
    }

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
//	    serverUpTest();
	}

	public static void serverUpTest() throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();

	    int attempts = 50;
        while (attempts-- > 0) {
            try {
                String url = "http://localhost:8080";
                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, RequestEntity.EMPTY,
                        String.class);
                logger.info("Response : {}", response);
                if (response.getStatusCode() == HttpStatus.OK) {
                    break;
                } else {
                    logger.info("Server not up: ? response: {}", response.getStatusCode());
                    Thread.sleep(1 * 1000);
                }
            } catch (ResourceAccessException e) {
                if (e.getCause() instanceof ConnectException) {
                    logger.info("Server not up? :{}", e.getMessage());
                } else {
                    throw e;
                }
            }

        }
    }
}