package com.swacorp.crew.microservices.core.qualifications.listener;

import com.swacorp.crew.microservices.core.qualifications.domain.Qualification;
import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualifications.service.QualificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.GenericMessage;

import java.net.URI;

/**
 * Created by x220804 on 6/23/2016.
 */
@EnableBinding(Sink.class)
public class QualificationTypesListener {
    @Autowired
    QualificationTypeService qualTransformService;

    @StreamListener(Sink.INPUT)
    public void consumeQualification(GenericMessage qualificationDate) {
        Qualification qual = qualTransformService.transformQualiticationType((QualificationType) qualificationDate.getPayload());

        System.out.println(qualTransformService.persistQualificationType2Oracle(qual));
    }
}
