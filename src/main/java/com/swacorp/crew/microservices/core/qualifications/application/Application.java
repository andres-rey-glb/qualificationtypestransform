package com.swacorp.crew.microservices.core.qualifications.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import java.io.IOException;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.swacorp.crew.microservices.core.qualifications.service", "com.swacorp.crew.microservices.core.qualifications.listener"})
@EnableDiscoveryClient
@EnableResourceServer
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }
}
