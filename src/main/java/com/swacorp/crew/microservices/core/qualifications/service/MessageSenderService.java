package com.swacorp.crew.microservices.core.qualifications.service;

import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;

/**
 * Created by POD Norris on 5/11/16.
 */
public interface MessageSenderService {
   
    boolean sendToStream(QualificationType qualification, String event);
}
