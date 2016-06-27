package com.swacorp.crew.microservices.core.qualifications.service;

import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualifications.domain.Qualification;

import java.util.List;

/**
 * Created by POD Norris on 5/11/16.
 */
public interface QualificationTypeService {

    Qualification transformQualiticationType(QualificationType gemfireQualType);
    Qualification persistQualificationType2Oracle(Qualification oracleQualType);
    Qualification updateQualificationType2Oracle(Qualification oracleQualType);
    void deleteQualificationType2Oracle(Qualification oracleQualType);
}
