package com.swacorp.crew.microservices.core.qualtype.exception;

import com.swacorp.crew.microservices.core.qualtype.domain.QualificationType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * NotFoundException class to modified custom error message
 * @author PodNorris
 */


@ResponseStatus (value = HttpStatus.NOT_FOUND, reason = "Custom message NotFoundException")
public class NotFoundException extends Exception{

    private QualificationType qualificationType;

    public NotFoundException(){
    }

    public NotFoundException(QualificationType qual){
        qualificationType = qual;
    }

    public QualificationType getQualificationType() {
        return qualificationType;
    }
}
