package com.swacorp.crew.microservices.core.qualifications.test.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.swacorp.crew.microservices.core.qualifications.service",
        "com.swacorp.crew.microservices.core.qualifications.listener"})
@EnableJpaRepositories(basePackages = "com.swacorp.crew.microservices.core.qualifications.repository")
@EntityScan(basePackages = "com.swacorp.crew.microservices.core.qualifications.domain")
@EnableBinding(Sink.class)
@EnableTransactionManagement
@Transactional
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

    public static void main(String[] args) throws IOException {
        SpringApplication application = new SpringApplication(QualificationTypeTestConfig.class);
        application.run(args);
    }
}