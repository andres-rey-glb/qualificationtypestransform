package com.swacorp.crew.microservices.core.qualtype.service;

import com.swacorp.crew.microservices.core.qualtype.domain.QualificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


import static java.util.Arrays.asList;

/**
 * Created by x220553 on 6/28/2016.
 */
@Service
@SuppressWarnings("unchecked")
public class ConsumeQualTypeDomainServiceImpl implements ConsumeQualTypeDomainService{

    @Resource
    private OAuth2RestTemplate restTemplate;

    //@Autowired
    //private ServiceUtils util;

    private static final String API = "/api/qualificationtype/";

    public void createQualDomain(QualificationType qualificationType, String url) {

        //URI uri = util.getServiceUrl("crew-qualificationtype-domain");
        //String url = uri.toString() + API;
        //String url = "http://sdchpld-vm10064.luv.ad.swacorp.com:51449"+ API;

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

    }

    public void deleteQualDomain(String url, Integer qualificationType) {

        try {
            url = url + qualificationType;
            Map<String, String> map = new HashMap<>();
            map.put("qualificationId", String.valueOf(qualificationType));
            restTemplate.delete(url, map);
        } catch (Exception ex) {
            ;
            ex.printStackTrace();
        }

    }

    public void updateQualDomain(String url, QualificationType qualification) {

        try {
            restTemplate.put(url + qualification.getQualificationId(), qualification);
            ResponseEntity<Map> apiResponse
                    = restTemplate.getForEntity(url + qualification.getQualificationId(), Map.class);

            if (apiResponse.getStatusCode().value() == HttpStatus.OK.value()) {
                System.out.println("QualificationType updated:" + qualification);
            } else {
                System.out.println("Error updating the QualificationType :" + qualification);
            }

            if (apiResponse.getBody() != null) {
                System.out.println(apiResponse.getBody());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
