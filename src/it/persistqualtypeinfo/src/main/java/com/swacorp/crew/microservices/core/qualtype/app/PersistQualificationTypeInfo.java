package com.swacorp.crew.microservices.core.qualifications.app;

/**
 * Created by x220553 on 6/28/2016.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.swacorp.crew.microservices.core.qualifications, com.swacorp.crew.microservices.core.qualificationtype.test"})
public class PersistQualificationTypeInfo {

    public static void main(String[] args) {
        SpringApplication.run(PersistQualificationTypeInfo.class, args);
    }
}
