package com.swacorp.crew.microservices.core.qualtype.listener;

import com.swacorp.crew.microservices.core.qualtype.domain.Qualification;
import com.swacorp.crew.microservices.core.qualtype.domain.QualificationType;
import com.swacorp.crew.microservices.core.qualtype.service.QualTypeTransformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.GenericMessage;

import java.util.logging.Logger;

/**
 * Created by x220804 on 6/23/2016.
 */
@EnableBinding(Sink.class)
public class QualTypeTransformationListener {
    private static final Logger LOG = Logger.getLogger(QualTypeTransformationListener.class.getName());

    @Autowired
    QualTypeTransformationService qualTransformService;

    @StreamListener(Sink.INPUT)
    public void consumeQualification(GenericMessage message) {
        LOG.info("------------ INSIDE LISTENER ------------");
        LOG.info("QUALIFICATION TYPE][MESSAGE RECIEVED]- [HEADER][EVENT TYPE] :"+message.getHeaders().get("eventType"));
        QualificationType qualificationType = (QualificationType) message.getPayload();
        LOG.info("[MESSAGE PAYLOAD] [QualificationType] : " + qualificationType.toString());
        Qualification qualification = qualTransformService.transformQualiticationType(qualificationType);
        LOG.info("[TRANSFORMED ENTITY] - [QUALIFICATION] : " + qualification.toString());

        if(message.getHeaders().get("eventType").equals("create")){
            qualTransformService.persistQualificationType2Oracle(qualification);
        }else if (message.getHeaders().get("eventType").equals("update")){
            qualTransformService.updateQualificationType2Oracle(qualification);
        }else{
            qualTransformService.deleteQualificationType2Oracle(qualification);
        }
    }
}
