package com.swacorp.crew.microservices.core.qualtype.repository;

import com.swacorp.crew.microservices.core.qualtype.domain.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {

}
