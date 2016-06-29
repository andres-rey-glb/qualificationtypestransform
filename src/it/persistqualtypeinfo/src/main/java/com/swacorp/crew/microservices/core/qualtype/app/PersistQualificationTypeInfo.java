package com.swacorp.crew.microservices.core.qualtype.app;

/**
 * Created by x220553 on 6/28/2016.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.swacorp.crew.microservices.core.qualtype, com.swacorp.crew.microservices.core.qualtype.test"})
@EnableDiscoveryClient
public class PersistQualificationTypeInfo {

    public static void main(String[] args) {
        SpringApplication.run(PersistQualificationTypeInfo.class, args);
    }
}
