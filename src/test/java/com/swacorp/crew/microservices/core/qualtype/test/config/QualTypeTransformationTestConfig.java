package com.swacorp.crew.microservices.core.qualtype.test.config;

import com.swacorp.crew.microservices.core.qualtype.service.QualTypeTransformationService;
import com.swacorp.crew.microservices.core.qualtype.service.QualTypeTransformationServiceImpl;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/*
@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.swacorp.crew.microservices.core.qualtype.listener"})
@EnableJpaRepositories(basePackages = "com.swacorp.crew.microservices.core.qualtype.repository")
@EntityScan(basePackages = "com.swacorp.crew.microservices.core.qualtype.domain")
@EnableBinding(Sink.class)*/
@Configuration
@EnableJpaRepositories(basePackages = "com.swacorp.crew.microservices.core.qualtype.repository")
@EntityScan(basePackages = "com.swacorp.crew.microservices.core.qualtype.domain")
@Import({ DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class QualTypeTransformationTestConfig {
    @Bean
    QualTypeTransformationService qualTypeTransformationService(){
        return new QualTypeTransformationServiceImpl();
    }
/*
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

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.swacorp.crew.microservices.core.qualtype.domain")
                .persistenceUnit("qualtypes-PU-test")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    public static void main(String[] args) throws IOException {
        SpringApplication application = new SpringApplication(QualTypeTransformationTestConfig.class);
        application.run(args);
    }*/
}