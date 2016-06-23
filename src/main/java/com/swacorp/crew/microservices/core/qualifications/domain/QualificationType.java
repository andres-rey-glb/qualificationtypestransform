package com.swacorp.crew.microservices.core.qualifications.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.Region;

import java.io.Serializable;

/**
 * Created by x222791 on 5/25/2016.
 */
@Region("QualificationType")
public class QualificationType implements Serializable  {

    @Id
    private Integer qualificationId;
    private String type;
    private String SubType;
    private Short displayLevel;
    private String station;
    private String position;
    private String group;
    private String deniedCountryId;
    private Short linesCode1;
    private Short linesCode2;
    private Short linesCode3;
    private Integer primary;
    private Integer expiresIn;
    private Integer blockMinRequired;
    private Integer subTypeBlockMinRequired;

    public QualificationType(){
    	
    }
    
    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return SubType;
    }

    public void setSubType(String subType) {
        SubType = subType;
    }

    public Short getDisplayLevel() {
        return displayLevel;
    }

    public void setDisplayLevel(Short displayLevel) {
        this.displayLevel = displayLevel;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDeniedCountryId() {
        return deniedCountryId;
    }

    public void setDeniedCountryId(String deniedCountryId) {
        this.deniedCountryId = deniedCountryId;
    }

    public Short getLinesCode1() {
        return linesCode1;
    }

    public void setLinesCode1(Short linesCode1) {
        this.linesCode1 = linesCode1;
    }

    public Short getLinesCode2() {
        return linesCode2;
    }

    public void setLinesCode2(Short linesCode2) {
        this.linesCode2 = linesCode2;
    }

    public Short getLinesCode3() {
        return linesCode3;
    }

    public void setLinesCode3(Short linesCode3) {
        this.linesCode3 = linesCode3;
    }

    public Integer getPrimary() {
        return primary;
    }

    public void setPrimary(Integer primary) {
        this.primary = primary;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Integer getBlockMinRequired() {
        return blockMinRequired;
    }

    public void setBlockMinRequired(Integer blockMinRequired) {
        this.blockMinRequired = blockMinRequired;
    }

    public Integer getSubTypeBlockMinRequired() {
        return subTypeBlockMinRequired;
    }

    public void setSubTypeBlockMinRequired(Integer subTypeBlockMinRequired) {
        this.subTypeBlockMinRequired = subTypeBlockMinRequired;
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(3, 15).
                append(qualificationId).
                toHashCode();
    }

    @Override
    public boolean equals(Object testObj) {
        if (testObj == null) {
            return false;
        }
        if (testObj == this) {
            return true;
        }
        if (testObj.getClass() != getClass()) {
            return false;
        }
        QualificationType test = (QualificationType) testObj;
        return new EqualsBuilder()
                .appendSuper(super.equals(testObj))
                .append(qualificationId, test.qualificationId)
                .isEquals();
    }
}
