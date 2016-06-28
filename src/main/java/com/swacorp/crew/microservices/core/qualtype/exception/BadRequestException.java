package com.swacorp.crew.microservices.core.qualtype.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * BadRequestException class to modified custom error message
 * @author PodNorris
 */
@ResponseStatus (value = HttpStatus.BAD_REQUEST, reason = "Custom message BadRequestException")
public class BadRequestException extends Exception {
    
}
