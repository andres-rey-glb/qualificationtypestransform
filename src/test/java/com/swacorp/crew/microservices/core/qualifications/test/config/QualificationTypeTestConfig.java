package com.swacorp.crew.microservices.core.qualifications.test.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.swacorp.crew.microservices.core.qualifications.service",
        "com.swacorp.crew.microservices.core.qualifications.listener"})
@EnableBinding(Sink.class)
public class QualificationTypeTestConfig {

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
}