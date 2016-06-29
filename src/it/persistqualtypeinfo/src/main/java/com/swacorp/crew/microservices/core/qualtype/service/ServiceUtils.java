package com.swacorp.crew.microservices.core.qualtype.service;

/**
 * Created by x220553 on 6/28/2016.
 */
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtils {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceUtils.class);
    @Autowired
    private LoadBalancerClient loadBalancer;

    public ServiceUtils() {
    }

    public URI getServiceUrl(String serviceId) {
        return this.getServiceUrl(serviceId, (String) null);
    }

    protected URI getServiceUrl(String serviceId, String fallbackUri) {
        URI uri = null;

        try {
            ServiceInstance e = this.loadBalancer.choose(serviceId);
            if (e == null) {
                throw new RuntimeException("Can\'t find a service with serviceId = " + serviceId);
            }

            uri = e.getUri();
            LOG.debug("Resolved serviceId \'{}\' to URL \'{}\'.", serviceId, uri);
        } catch (RuntimeException var5) {
            if (fallbackUri == null) {
                throw var5;
            }

            uri = URI.create(fallbackUri);
            LOG.warn("Failed to resolve serviceId \'{}\'. Fallback to URL \'{}\'.", serviceId, uri);
        }

        return uri;
    }

    public <T> ResponseEntity<T> createOkResponse(T body) {
        return this.createResponse(body, HttpStatus.OK);
    }

    public <T> ResponseEntity<T> createResponse(ResponseEntity<T> result) {
        ResponseEntity response = this.createResponse(result.getBody(), result.getStatusCode());
        return response;
    }

    public <T> ResponseEntity<T> createResponse(T body, HttpStatus httpStatus) {
        return new ResponseEntity(body, httpStatus);
    }
}


