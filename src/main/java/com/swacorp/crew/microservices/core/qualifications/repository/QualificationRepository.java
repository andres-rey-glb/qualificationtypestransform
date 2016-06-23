package com.swacorp.crew.microservices.core.qualifications.repository;

import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;
import org.springframework.data.repository.CrudRepository;

public interface QualificationRepository extends CrudRepository<QualificationType, Integer> {

}
