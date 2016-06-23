package com.swacorp.crew.microservices.core.qualifications.application;

import com.gemstone.gemfire.cache.GemFireCache;
import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import java.io.IOException;
import java.util.Properties;

//allows srping to configure runtime project
@EnableAutoConfiguration
@SpringBootApplication
//gemfire source package
@EnableGemfireRepositories(basePackages = "com.swacorp.crew.microservices.core.qualifications.repository")
@ComponentScan(basePackages = {"com.swacorp.crew.microservices.core.qualifications.service", "com.swacorp.crew.microservices.core.qualifications.api"})
//allows to read configuration from external gemfire
//@ImportResource("integration.xml")
@EnableDiscoveryClient
@EnableResourceServer
@EnableBinding(Source.class)
public class Application {

    @Bean
    Properties gemfireProperties() {
        Properties gemfireProperties = new Properties();
        gemfireProperties.setProperty("name", "DataGemFireApplication");
        gemfireProperties.setProperty("mcast-port", "0");
        gemfireProperties.setProperty("log-level", "config");
        return gemfireProperties;
    }

    @Bean
    CacheFactoryBean gemfireCache() {
        CacheFactoryBean gemfireCache = new CacheFactoryBean();
        gemfireCache.setClose(true);
        gemfireCache.setProperties(gemfireProperties());
        gemfireCache.setPdxPersistent(true);
        return gemfireCache;
    }

    @Bean
    LocalRegionFactoryBean<Integer, QualificationType> qualificaionRegion(final GemFireCache cache) {
        LocalRegionFactoryBean<Integer, QualificationType> region = new LocalRegionFactoryBean<>();
        region.setCache(cache);
        region.setClose(false);
        region.setName("QualificationType");
        region.setPersistent(true);
        return region;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication application = new SpringApplication(Application.class);
        application.setWebEnvironment(true);
        application.run(args);
    }
}
