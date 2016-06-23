package com.swacorp.crew.microservices.core.qualifications.api;

import java.io.Serializable;

/**
 *
 * @author x220551
 */
public class QualType implements Serializable {
    
    private String qualType;
    private String qualSubType;

    public String getQualType() {
        return qualType;
    }

    public void setQualType(String qualType) {
        this.qualType = qualType;
    }

    public String getQualSubType() {
        return qualSubType;
    }

    public void setQualSubType(String qualSubType) {
        this.qualSubType = qualSubType;
    }
}
