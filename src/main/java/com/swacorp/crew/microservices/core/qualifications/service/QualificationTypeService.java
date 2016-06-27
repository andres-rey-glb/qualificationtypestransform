package com.swacorp.crew.microservices.core.qualifications.service;

import com.swacorp.crew.microservices.core.qualifications.domain.Qualification;
import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;

import java.util.List;

/**
 * Created by POD Norris on 5/11/16.
 */
public interface QualificationTypeService {

    Qualification transformQualiticationType(QualificationType gemfireQualType);
    boolean persistQualificationType2Oracle(QualificationType oracleQualType);
}
