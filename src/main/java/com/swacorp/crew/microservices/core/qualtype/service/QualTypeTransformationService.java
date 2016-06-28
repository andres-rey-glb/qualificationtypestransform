package com.swacorp.crew.microservices.core.qualtype.service;

import com.swacorp.crew.microservices.core.qualtype.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualtype.domain.Qualification;

/**
 * Created by POD Norris on 5/11/16.
 */
public interface QualTypeTransformationService {

    Qualification transformQualiticationType(QualificationType gemfireQualType);
    Qualification persistQualificationType2Oracle(Qualification oracleQualType);
    Qualification updateQualificationType2Oracle(Qualification oracleQualType);
    void deleteQualificationType2Oracle(Qualification oracleQualType);
}
