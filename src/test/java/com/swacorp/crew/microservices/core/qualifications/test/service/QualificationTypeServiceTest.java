package com.swacorp.crew.microservices.core.qualifications.test.service;

import com.swacorp.crew.microservices.core.qualifications.repository.QualificationRepository;
import com.swacorp.crew.microservices.core.qualifications.test.config.QualificationTypeTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by POD Norris on 5/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QualificationTypeTestConfig.class)
@WebIntegrationTest({"server.port: 0"})
public class QualificationTypeServiceTest {
    @Autowired
    QualificationRepository repository;

    @Test
    public void test(){}

}
