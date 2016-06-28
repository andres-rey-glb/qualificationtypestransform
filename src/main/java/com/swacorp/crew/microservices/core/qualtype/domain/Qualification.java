package com.swacorp.crew.microservices.core.qualtype.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by x220553 on 6/23/2016.
 */
@Entity
@Table(name = "QUALIFICATION", uniqueConstraints = @UniqueConstraint(columnNames = {"qualtype", "qualsubtype"}))
public class Qualification {
    @Id
    private Long qualseqnumber;
    private String qualtype;
    private String qualsubtype;
    private Long qualdisplaylevel;
    private String quallinescode1;
    private String quallinescode2;
    private String quallinescode3;
    private Long qualprimary;
    private String station;
    private String position;
    private Long expires_in;
    private Long block_min_required;
    private Long subtype_block_min_required;

    public Long getQualseqnumber() {
        return qualseqnumber;
    }

    public void setQualseqnumber(Long qualseqnumber) {
        this.qualseqnumber = qualseqnumber;
    }

    public String getQualtype() {
        return qualtype;
    }

    public void setQualtype(String qualtype) {
        this.qualtype = qualtype;
    }

    public String getQualsubtype() {
        return qualsubtype;
    }

    public void setQualsubtype(String qualsubtype) {
        this.qualsubtype = qualsubtype;
    }

    public Long getQualdisplaylevel() {
        return qualdisplaylevel;
    }

    public void setQualdisplaylevel(Long qualdisplaylevel) {
        this.qualdisplaylevel = qualdisplaylevel;
    }

    public String getQuallinescode1() {
        return quallinescode1;
    }

    public void setQuallinescode1(String quallinescode1) {
        this.quallinescode1 = quallinescode1;
    }

    public String getQuallinescode2() {
        return quallinescode2;
    }

    public void setQuallinescode2(String quallinescode2) {
        this.quallinescode2 = quallinescode2;
    }

    public String getQuallinescode3() {
        return quallinescode3;
    }

    public void setQuallinescode3(String quallinescode3) {
        this.quallinescode3 = quallinescode3;
    }

    public Long getQualprimary() {
        return qualprimary;
    }

    public void setQualprimary(Long qualprimary) {
        this.qualprimary = qualprimary;
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

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public Long getBlock_min_required() {
        return block_min_required;
    }

    public void setBlock_min_required(Long block_min_required) {
        this.block_min_required = block_min_required;
    }

    public Long getSubtype_block_min_required() {
        return subtype_block_min_required;
    }

    public void setSubtype_block_min_required(Long subtype_block_min_required) {
        this.subtype_block_min_required = subtype_block_min_required;
    }
}