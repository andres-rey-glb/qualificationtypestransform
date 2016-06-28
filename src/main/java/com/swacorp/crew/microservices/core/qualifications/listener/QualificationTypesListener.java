package com.swacorp.crew.microservices.core.qualifications.listener;

import com.swacorp.crew.microservices.core.qualifications.domain.Qualification;
import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualifications.service.QualificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.GenericMessage;

/**
 * Created by x220804 on 6/23/2016.
 */
@EnableBinding(Sink.class)
public class QualificationTypesListener {
    @Autowired
    QualificationTypeService qualTransformService;

    @StreamListener(Sink.INPUT)
    public void consumeQualification(GenericMessage message) {
        System.out.println("------------ INSIDE LISTENER ------------");
        System.out.println("HEADER :"+message.getHeaders().get("eventType"));
        QualificationType qualificationType = (QualificationType) message.getPayload();
        System.out.println("QualificationType = " + qualificationType.toString());
        Qualification qualification = qualTransformService.transformQualiticationType(qualificationType);
        System.out.println("Qualification = " + qualification.toString());
        if(message.getHeaders().get("eventType").equals("create")){
            qualTransformService.persistQualificationType2Oracle(qualification);
        }else if (message.getHeaders().get("eventType").equals("update")){
            qualTransformService.updateQualificationType2Oracle(qualification);
        }else{
            qualTransformService.deleteQualificationType2Oracle(qualification);
        }


    }
}
