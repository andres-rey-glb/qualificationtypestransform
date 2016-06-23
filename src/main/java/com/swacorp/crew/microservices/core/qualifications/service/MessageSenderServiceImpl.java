package com.swacorp.crew.microservices.core.qualifications.service;

import com.swacorp.crew.microservices.core.qualifications.domain.QualificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by x220804 on 6/15/2016.
 */
@Service
public class MessageSenderServiceImpl implements MessageSenderService{
    private static final Logger LOG = Logger.getLogger(MessageSenderServiceImpl.class.getName());

    @Autowired
    Source source;

    public boolean sendToStream(QualificationType qualification, String event) {
        boolean messageSent = false;
        try{
            messageSent = source.output().send(MessageBuilder.withPayload(qualification).setHeader("eventType",event).build());
        }catch(Exception ex){
            LOG.warning("QUALIFICATION TYPES: Message to stream not sent: "+ex.getMessage());
        }
        return messageSent;
    }
}
