package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyINDOITSDO extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "startTimeOfHalfHrPeriod")
    public String startTimeOfHalfHrPeriod;

    @XmlElement(name = "systemZone")
    public String systemZone;

    @XmlElement(name = "settlementPeriod")
    public String settlementPeriod;

    @XmlElement(name = "publishingPeriodCommencingTime")
    public String publishingPeriodCommencingTime;

    @XmlElement(name = "demand")
    public String demand;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + startTimeOfHalfHrPeriod
                + ":" + systemZone
                + ":" + settlementPeriod
                + ":" + publishingPeriodCommencingTime
                + ":" + demand
                + ":" + activeFlag
                ;
    }

}
