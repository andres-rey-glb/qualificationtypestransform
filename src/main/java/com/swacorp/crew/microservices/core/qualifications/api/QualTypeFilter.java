package com.swacorp.crew.microservices.core.qualifications.api;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by x220551
 */
public class QualTypeFilter implements Serializable {

    @NotNull
    private Integer qualificationId;
    private List<QualType> qualTypes;
    private String station;
    private String position;
    private String group;

    public Integer getQualificationId() {
        return qualificationId;
    }

    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    public List<QualType> getQualTypes() {
        return qualTypes;
    }

    public void setQualTypes(List<QualType> qualTypes) {
        this.qualTypes = qualTypes;
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
}