package com.swacorp.crew.microservices.core.qualtype.service;

import com.swacorp.crew.microservices.core.qualtype.domain.Qualification;
import com.swacorp.crew.microservices.core.qualtype.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualtype.repository.QualificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.logging.Logger;

@Service
public class QualTypeTransformationServiceImpl implements QualTypeTransformationService {
    private static final Logger LOG = Logger.getLogger(QualTypeTransformationServiceImpl.class.getName());
    @Autowired
    QualificationRepository repository;

    @Override
    public Qualification transformQualiticationType(QualificationType gemfireQualType) {
        return transformQualificationData2Oracle.apply(gemfireQualType);
    }

    @Override
    public Qualification persistQualificationType2Oracle(Qualification oracleQualType) {
        Qualification createdQualification = null;
        createdQualification = repository.saveAndFlush(oracleQualType);
        LOG.info("[QUALIFICATION TYPES]-[CREATE]: Qualification created in Oracle DB. QUALSEQNUMBER: "
                + oracleQualType.getQualseqnumber());
        return createdQualification;
    }

    @Override
    public Qualification updateQualificationType2Oracle(Qualification oracleQualType) {
        Qualification updatedQualification = null;
        updatedQualification = repository.saveAndFlush(oracleQualType);
        LOG.info("[QUALIFICATION TYPES]-[UPDATE]: Qualification updated in Oracle DB. QUALSEQNUMBER: "
                + oracleQualType.getQualseqnumber());
        return updatedQualification;
    }

    @Override
    public void deleteQualificationType2Oracle(Qualification oracleQualType) {
        repository.delete(oracleQualType);
        LOG.info("[QUALIFICATION TYPES]-[DELETE]: Qualification deleted in Oracle DB. QUALSEQNUMBER: "
                + oracleQualType.getQualseqnumber());
    }

    private Function<QualificationType, Qualification> transformQualificationData2Oracle
            = new Function<QualificationType, Qualification>() {

        public Qualification apply(QualificationType qualificationType) {

            Qualification qualification = new Qualification();

            qualification.setQualseqnumber(qualificationType.getQualificationId() != null ?
                    qualificationType.getQualificationId().longValue() : null);
            qualification.setQualtype(qualificationType.getType());
            qualification.setQualsubtype(qualificationType.getSubType());
            qualification.setQualdisplaylevel(qualificationType.getDisplayLevel() != null ?
                    qualificationType.getDisplayLevel().longValue() : null);
            qualification.setQuallinescode1(qualificationType.getLinesCode1() != null ?
                    qualificationType.getLinesCode1().toString() : null);
            qualification.setQuallinescode2(qualificationType.getLinesCode2() != null ?
                    qualificationType.getLinesCode2().toString() : null);
            qualification.setQuallinescode3(qualificationType.getLinesCode3() != null ?
                    qualificationType.getLinesCode3().toString() : null);
            qualification.setQualprimary(qualificationType.getPrimary() != null ?
                    qualificationType.getPrimary().longValue() : null);
            qualification.setStation(qualificationType.getStation());
            qualification.setPosition(qualificationType.getPosition());
            qualification.setExpires_in(qualificationType.getExpiresIn() != null ?
                    qualificationType.getExpiresIn().longValue() : null);
            qualification.setBlock_min_required(qualificationType.getBlockMinRequired() != null ?
                    qualificationType.getBlockMinRequired().longValue() : null);
            qualification.setSubtype_block_min_required(qualificationType.getSubTypeBlockMinRequired() != null ?
                    qualificationType.getSubTypeBlockMinRequired().longValue() : null);


            return qualification;
        }
    };
}
