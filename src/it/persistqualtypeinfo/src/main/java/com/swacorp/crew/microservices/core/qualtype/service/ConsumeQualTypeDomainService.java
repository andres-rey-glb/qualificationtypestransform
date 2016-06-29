package com.swacorp.crew.microservices.core.qualtype.service;

import com.swacorp.crew.microservices.core.qualtype.domain.QualificationType;
import org.springframework.stereotype.Service;

/**
 * Created by x220553 on 6/28/2016.
 */
@Service
public interface ConsumeQualTypeDomainService {

    void createQualDomain(QualificationType qualificationType, String url);

    void deleteQualDomain(String url, Integer qualificationType);

    public void updateQualDomain(String url, QualificationType qualification);

}
