package com.swacorp.crew.microservices.core.qualifications.service;

import com.swacorp.crew.microservices.core.qualifications.api.QualTypeFilter;
import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;

import java.util.List;

/**
 * Created by POD Norris on 5/11/16.
 */
public interface QualificationTypeService {
   
    QualificationType create(QualificationType qualificationType);

    List<QualificationType> findAll();
    
    List<QualificationType> findByFilter(QualTypeFilter filter);

    QualificationType findById(Integer l);

    QualificationType update(QualificationType qualificationTypeToUpdate);

    void delete(QualificationType qualificationTypeToDelete);
}
