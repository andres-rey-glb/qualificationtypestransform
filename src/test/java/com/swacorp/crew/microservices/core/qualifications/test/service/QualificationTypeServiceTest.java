package com.swacorp.crew.microservices.core.qualifications.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swacorp.crew.microservices.core.qualifications.api.QualTypeFilter;
import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualifications.test.config.QualificationTypeTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by POD Norris on 5/11/16.
 */
//Spring JunitConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QualificationTypeTestConfig.class)
//Allows to get an ramdom aviable port
@WebIntegrationTest({"server.port: 0"})
public class QualificationTypeServiceTest {

    /**
     * A random available port is injected into the port field just for testing
     * purposes.
     */
    @Value("${local.server.port}")
    private int port;
    //test ID value
    private final String LOG_ID = "123456";

    //Required to Generate JSON content from Java objects
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * Create an entity ChangeLog into a region
     * @throws Exception 
     */
    @Test
    public void createQualificationTypeEntryTest() throws Exception {

        //Test RestTemplate to invoke the API.
        RestTemplate restTemplate = new TestRestTemplate();
        String SERVER_URL = "http://localhost:" + port;
        String API = "/api/qualificationtype";

        //Building the Request body data
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("qualificationId", LOG_ID);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Creating http entity object with request body and headers
        HttpEntity<String> httpEntity
                = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);

        //Invoking the API
        ResponseEntity<Map> apiResponse
                = restTemplate.postForEntity(SERVER_URL + API, httpEntity, Map.class);

        assertEquals(HttpStatus.CREATED, apiResponse.getStatusCode());

    }

    /**
     * Get a ChangeLog entry from local region by ID
     * @throws Exception 
     */
    @Test
    public void getQualificationTypeByIdTest() throws Exception {
        createQualificationTypeEntryTest();
        //Test RestTemplate to invoke the API.
        RestTemplate restTemplate = new TestRestTemplate();
        String SERVER_URL = "http://localhost:" + port;
        String API = "/api/qualificationtype/";
        //Invoking the API
        ResponseEntity<Map> apiResponse
                = restTemplate.getForEntity(SERVER_URL + API + LOG_ID, Map.class);

        assertEquals(HttpStatus.OK, apiResponse.getStatusCode());
        assertNotNull(apiResponse.getBody());
    }

    @Test
    public void getQualificationTypeByFilterTest() throws Exception {
        createQualificationTypeEntryTest();
        //Test RestTemplate to invoke the API.
        RestTemplate restTemplate = new TestRestTemplate();
        String SERVER_URL = "http://localhost:" + port;
        String API = "/api/qualificationtype/filter";
        
        
        
        //Invoking the API
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        QualTypeFilter searchFilter = new QualTypeFilter();
        searchFilter.setQualificationId(Integer.parseInt(LOG_ID));

        //Creating http entity object with request body and headers
        HttpEntity<String> httpEntity
                = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(searchFilter), requestHeaders);

        //Invoking the API
        ResponseEntity<QualificationType[]> apiResponse
                = restTemplate.postForEntity(SERVER_URL + API, httpEntity, QualificationType[].class);

        assertEquals(HttpStatus.OK, apiResponse.getStatusCode());
        assertNotNull(apiResponse.getBody());
    }
    
    /**
     * Get a ChangeLog entry from local region by ID, to test
     * a BadRequest response.
     * @throws Exception 
     */
    @Test
    public void getQualificationTypeByIdBadRequesTest() throws Exception {
        
        //Test RestTemplate to invoke the API.
        RestTemplate restTemplate = new TestRestTemplate();
        String SERVER_URL = "http://localhost:" + port;
        String API = "/api/qualificationtype";
        String badChangeLogID = "/badrequest";
        //Invoking the API
        ResponseEntity<Map> apiResponse
                = restTemplate.getForEntity(SERVER_URL + API + badChangeLogID, Map.class);

        assertEquals(HttpStatus.BAD_REQUEST, apiResponse.getStatusCode());
        assertNotNull(apiResponse.getBody());
    }

    /**
     * test the update for an entry changeLog based on given ID
     * @throws Exception 
     */
    @Test
    public void getQualificationTypeUpdateTest() throws Exception {
        createQualificationTypeEntryTest();
        //Test RestTemplate to invoke the API.
        RestTemplate restTemplate = new TestRestTemplate();
        String SERVER_URL = "http://localhost:" + port;
        String API = "/api/qualificationtype/";

        Map<String, String> map = new HashMap<String, String>();
        map.put("qualificationId", LOG_ID);
        //update values
        map.put("qualifcationTypeUserID", "x222791");
        restTemplate.put(SERVER_URL + API + LOG_ID, null, map);

        //Invoking the API
        ResponseEntity<Map> apiResponse
                = restTemplate.getForEntity(SERVER_URL + API + LOG_ID, Map.class);

        assertEquals(HttpStatus.OK, apiResponse.getStatusCode());
        assertNotNull(apiResponse.getBody());
    }
    
    /**
     * Test delete operation for an changeLog entity
     * @throws Exception 
     */
    @Test
    public void getQualificationTypeDeleteTest() throws Exception {
        createQualificationTypeEntryTest();
        //Test RestTemplate to invoke the API.
        RestTemplate restTemplate = new TestRestTemplate();        
        String SERVER_URL = "http://localhost:" + port;
        String API = "/api/qualificationtype";
        String qualificationTypeId = "/"+LOG_ID;
        
        Map<String, String> map = new HashMap<>();
        map.put("qualificationId", LOG_ID);
        
        //Invoking the API        
        ResponseEntity<Map> apiResponse
                = restTemplate.getForEntity(SERVER_URL + API + qualificationTypeId, Map.class);

        assertEquals(HttpStatus.OK, apiResponse.getStatusCode());
        assertNotNull(apiResponse.getBody());
        
        //delete
        restTemplate.delete(SERVER_URL + API + qualificationTypeId,map);
        
        apiResponse = restTemplate.getForEntity(SERVER_URL + API + qualificationTypeId, Map.class);
        assertEquals(HttpStatus.NOT_FOUND, apiResponse.getStatusCode());
        
    }   
    
}
