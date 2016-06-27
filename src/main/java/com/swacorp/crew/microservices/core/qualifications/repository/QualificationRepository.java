package com.swacorp.crew.microservices.core.qualifications.repository;

import com.swacorp.crew.microservices.core.qualifications.domain.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {

}
