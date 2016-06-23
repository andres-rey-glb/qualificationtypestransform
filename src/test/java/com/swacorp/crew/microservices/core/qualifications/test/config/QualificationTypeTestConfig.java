package com.swacorp.crew.microservices.core.qualifications.test.config;

import com.gemstone.gemfire.cache.GemFireCache;
import java.io.IOException;
import java.util.Properties;

import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.gemfire.CacheFactoryBean;
import org.springframework.data.gemfire.LocalRegionFactoryBean;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//allows spring to configure runtime project
@EnableAutoConfiguration
@SpringBootApplication
//gemfire source package
@EnableGemfireRepositories(basePackages = "com.swacorp.crew.microservices.core.qualifications.repository")
@ComponentScan(basePackages = {"com.swacorp.crew.microservices.core.qualifications.service","com.swacorp.crew.microservices.core.qualifications.api"})
@EnableBinding(Source.class)
public class QualificationTypeTestConfig {
    
    @Configuration
    static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
        public WebSecurityConfiguration(){}

        @Override
        protected void configure(HttpSecurity http) throws Exception {
        	http.csrf().disable();
            http.authorizeRequests().antMatchers("/test/**").anonymous();
        }
    }
    
    /**
     * 
     * @return GemFire Properties
     */
    @Bean
    Properties gemfireProperties() {
        Properties gemfireProperties = new Properties();
        gemfireProperties.setProperty("name", "DataGemFireApplication");
        gemfireProperties.setProperty("mcast-port", "0");
        gemfireProperties.setProperty("log-level", "config");
        return gemfireProperties;
    }

    /**
     * 
     * @return CacheFactoryBean
     */
    @Bean
    CacheFactoryBean gemfireCache() {
        CacheFactoryBean gemfireCache = new CacheFactoryBean();
        gemfireCache.setClose(true);
        gemfireCache.setProperties(gemfireProperties());
        gemfireCache.setPdxPersistent(true);
        return gemfireCache;
    }

    /**
     * Configure Local region
     * @param cache
     * @return 
     */
    @Bean
    LocalRegionFactoryBean<Integer, QualificationType> changeLogRegion(final GemFireCache cache) {
        LocalRegionFactoryBean<Integer, QualificationType> region = new LocalRegionFactoryBean<>();
        region.setCache(cache);
        region.setClose(false);
        region.setName("QualificationType");
        region.setPersistent(false);
        return region;
    }

    public static void main(String[] args) throws IOException {
        SpringApplication application = new SpringApplication(QualificationTypeTestConfig.class);
        application.setWebEnvironment(true);
        application.run(args);
    }
}
