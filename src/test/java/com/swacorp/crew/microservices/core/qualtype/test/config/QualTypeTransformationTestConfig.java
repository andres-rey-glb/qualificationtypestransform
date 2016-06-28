package com.swacorp.crew.microservices.core.qualtype.test.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.swacorp.crew.microservices.core.qualtype.service",
        "com.swacorp.crew.microservices.core.qualtype.listener",
        "com.swacorp.crew.microservices.core.qualtype.repository"})
@EnableJpaRepositories(basePackages = "com.swacorp.crew.microservices.core.qualtype.repository")
@EntityScan(basePackages = "com.swacorp.crew.microservices.core.qualtype.domain")
@EnableBinding(Sink.class)
@Transactional
public class QualTypeTransformationTestConfig {

    @Configuration
    static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
        public WebSecurityConfiguration() {
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            http.authorizeRequests().antMatchers("/test/**").anonymous();
        }
    }

    public static void main(String[] args) throws IOException {
        SpringApplication application = new SpringApplication(QualTypeTransformationTestConfig.class);
        application.run(args);
    }
}