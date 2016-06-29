package com.swacorp.crew.microservices.core.qualtype.test;


import com.swacorp.crew.microservices.core.qualtype.app.PersistQualificationTypeInfo;
import com.swacorp.crew.microservices.core.qualtype.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualtype.service.ConsumeQualTypeDomainService;
import com.swacorp.crew.microservices.core.qualtype.service.ServiceUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;

import static java.util.Arrays.asList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PersistQualificationTypeInfo.class)
public class PersistQualificationTypeInfoTests {

    @Autowired
    private ConsumeQualTypeDomainService consumeQualTypeDomainService;

    //@Autowired
    //private ServiceUtils util;

    private static final String API = "/api/qualificationtype/";

    @Test
    public void createQualDomain() {

        //URI uri = util.getServiceUrl("crew-qualificationtype-domain");
        //String url = uri.toString() + API;
        String url = "http://sdchpld-vm10064.luv.ad.swacorp.com:51449"+ API;

        consumeQualTypeDomainService.createQualDomain(createQualificationType(),url);

        /**
         *

        URI uri = util.getServiceUrl("crew-qualificationType-domain");
        String url = uri.toString() + API;

        QualificationType qualificationType = createQualificationType();

        try {

            restTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
            ResponseEntity<HashMap> apiResponse = restTemplate.postForEntity(url, qualificationType, HashMap.class);
            if (apiResponse.getStatusCode().value() == HttpStatus.CREATED.value()) {
                System.out.println("CrewQualificationType created:" + qualificationType);
            } else {
                System.out.println("Error creating the CrewQualificationType :" + qualificationType);
            }
        } catch (Exception ex) {
            System.out.println("----------- Error ----------");
            ex.printStackTrace();
        }

         */

    }

    @Test
    public void updateQualDomain() {

        String url = "http://sdchpld-vm10064.luv.ad.swacorp.com:51449"+ API;


        consumeQualTypeDomainService.updateQualDomain(url,updateQualificationType());

    }

    @Test
    public void deleteQualDomain() {

        String url = "http://sdchpld-vm10064.luv.ad.swacorp.com:51449"+ API;

        consumeQualTypeDomainService.deleteQualDomain(url,createQualificationType().getQualificationId());
    }

    private QualificationType createQualificationType() {

        QualificationType qualificationType = new QualificationType();

        qualificationType.setQualificationId(9812245);
        qualificationType.setType("54623");
        qualificationType.setDisplayLevel((short)283);
        qualificationType.setStation("WW");
        qualificationType.setPosition("PP");
        qualificationType.setGroup(null);
        qualificationType.setDeniedCountryId("0");
        qualificationType.setLinesCode1((short)0);
        qualificationType.setLinesCode2((short)0);
        qualificationType.setLinesCode3((short)0);
        qualificationType.setPrimary(0);
        qualificationType.setExpiresIn(0);
        qualificationType.setBlockMinRequired(0);
        qualificationType.setSubTypeBlockMinRequired(0);
        qualificationType.setSubType("90761");

        return qualificationType;
    }

    private QualificationType updateQualificationType() {

        QualificationType qualificationType = new QualificationType();

        qualificationType.setQualificationId(9812245);
        qualificationType.setType("54623");
        qualificationType.setDisplayLevel((short)283);
        qualificationType.setStation("XX");
        qualificationType.setPosition("HH");
        qualificationType.setGroup(null);
        qualificationType.setDeniedCountryId("0");
        qualificationType.setLinesCode1((short)0);
        qualificationType.setLinesCode2((short)0);
        qualificationType.setLinesCode3((short)0);
        qualificationType.setPrimary(0);
        qualificationType.setExpiresIn(0);
        qualificationType.setBlockMinRequired(0);
        qualificationType.setSubTypeBlockMinRequired(0);
        qualificationType.setSubType("90761");

        return qualificationType;
    }


}
