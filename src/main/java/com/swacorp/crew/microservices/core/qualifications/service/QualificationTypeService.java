package com.swacorp.crew.microservices.core.qualifications.service;

import com.swacorp.crew.microservices.core.qualifications.api.QualTypeFilter;
import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;

import java.util.List;

/**
 * Created by POD Norris on 5/11/16.
 */
public interface QualificationTypeService {
   
    Object qualiticationType2Oracle(QualificationType gemfireQualType);
}
