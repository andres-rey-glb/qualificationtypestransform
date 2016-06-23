package com.swacorp.crew.microservices.core.qualifications.listener;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * Created by x220804 on 6/23/2016.
 */
@EnableBinding(Sink.class)
public class QualificationTypesListener {
}
