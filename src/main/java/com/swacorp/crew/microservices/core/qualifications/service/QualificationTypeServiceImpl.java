package com.swacorp.crew.microservices.core.qualifications.service;

import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QualificationTypeServiceImpl implements QualificationTypeService {

    @Override
    public Object transformQualiticationType(QualificationType gemfireQualType) {
        return null;
    }

    @Override
    public boolean persistQualificationType2Oracle(QualificationType oracleQualType) {
        return false;
    }
}
