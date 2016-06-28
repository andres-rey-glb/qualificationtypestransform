package com.swacorp.crew.microservices.core.qualificationtype.test;


import com.swacorp.crew.microservices.core.qualifications.app.PersistQualificationTypeInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistQualificationTypeInfo.class)
public class PersistQualificationTypeInfoTests {

    @Test
    public void test(){
        System.out.println("------TEST EXECUTED --------");
    }


}
