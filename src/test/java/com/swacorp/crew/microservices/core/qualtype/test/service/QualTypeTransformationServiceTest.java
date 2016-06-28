package com.swacorp.crew.microservices.core.qualtype.test.service;

import com.swacorp.crew.microservices.core.qualtype.domain.Qualification;
import com.swacorp.crew.microservices.core.qualtype.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualtype.repository.QualificationRepository;
import com.swacorp.crew.microservices.core.qualtype.service.QualTypeTransformationService;
import com.swacorp.crew.microservices.core.qualtype.test.config.QualTypeTransformationTestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by POD Norris on 5/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = QualTypeTransformationTestConfig.class)
@Transactional
@DirtiesContext
public class QualTypeTransformationServiceTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Autowired
    private QualificationRepository repository;

    @Autowired
    private QualTypeTransformationService service;

    private Qualification qualificationInput1;
    private Qualification qualificationInput2;
    private QualificationType qualificationTypeInput;

    @Before
    public void setUp(){
        qualificationInput1 = new Qualification();
        qualificationInput1.setQualseqnumber(new Long("123"));
        qualificationInput1.setQualdisplaylevel(new Long("456"));
        qualificationInput1.setQualsubtype("SUB01");
        qualificationInput1.setQualtype("PTPS");

        qualificationInput2 = new Qualification();
        qualificationInput2.setQualseqnumber(new Long("798"));
        qualificationInput2.setQualdisplaylevel(new Long("358"));
        qualificationInput2.setQualsubtype("SUB02");
        qualificationInput2.setQualtype("PPS");

        qualificationTypeInput = new QualificationType();
        qualificationTypeInput.setQualificationId(new Integer("798"));
        qualificationTypeInput.setDisplayLevel(new Short("358"));
        qualificationTypeInput.setSubType("SUB02");
        qualificationTypeInput.setType("PPS");
    }

    @Test
    public void transformQualificationTypeToOracleQualificationEntityTest(){
        Qualification transformed = service.transformQualiticationType(qualificationTypeInput);
        Qualification expected = qualificationInput2;

        Assert.assertEquals(expected,transformed);
    }

    @Test
    public void createOracleQualificationTest(){
        repository.deleteAll();
        Assert.assertEquals(repository.findAll().size(),0);

        repository.saveAndFlush(qualificationInput1);
        repository.saveAndFlush(qualificationInput2);

        Assert.assertEquals(repository.findAll().size(),2);
    }

    @Test
    public void updateOracleQualificationTest(){
        createOracleQualificationTest();
        Qualification toUpdate = repository.findOne(new Long("123"));
        toUpdate.setQualsubtype("TTY");
        Qualification updated = repository.saveAndFlush(toUpdate);
        Assert.assertEquals(toUpdate,updated);
    }

    @Test
    public void deleteOracleQualificationTest(){
        createOracleQualificationTest();
        Assert.assertEquals(repository.findAll().size(),2);
        Qualification qualification = repository.findOne(new Long("123"));
        repository.delete(qualification);
        Assert.assertEquals(repository.findAll().size(),1);
    }

    @Test
    public void persistQualificationToOracleDB(){
        Qualification createdQualification = service.persistQualificationType2Oracle(qualificationInput1);
        Assert.assertEquals(qualificationInput1,createdQualification);
    }
}
