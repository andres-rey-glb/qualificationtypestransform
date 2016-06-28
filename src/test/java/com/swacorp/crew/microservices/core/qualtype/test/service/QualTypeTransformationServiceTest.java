package com.swacorp.crew.microservices.core.qualtype.test.service;

import com.swacorp.crew.microservices.core.qualtype.repository.QualificationRepository;
import com.swacorp.crew.microservices.core.qualtype.service.QualTypeTransformationService;
import com.swacorp.crew.microservices.core.qualtype.test.config.QualTypeTransformationTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by POD Norris on 5/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QualTypeTransformationTestConfig.class)
@ComponentScan(basePackages = {"com.swacorp.crew.microservices.core.qualtype.service",
        "com.swacorp.crew.microservices.core.qualtype.listener",
        "com.swacorp.crew.microservices.core.qualtype.repository"})
public class QualTypeTransformationServiceTest {
    @Autowired
    QualTypeTransformationService service;

    @Autowired
    QualificationRepository repository;

    @Test
    public void test(){
        Assert.assertNotNull(service);
        Assert.assertNotNull(repository);
    }

}
