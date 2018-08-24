package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyMELIMBALNGC extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "settlementDate")
    public String settlementDate;

    @XmlElement(name = "settlementPeriod")
    public String settlementPeriod;

    @XmlElement(name = "nationalBoundaryIdentifier")
    public String nationalBoundaryIdentifier;

    @XmlElement(name = "publishingPeriodCommencingTime")
    public String publishingPeriodCommencingTime;

    @XmlElement(name = "imbalanceValue")
    public String imbalanceValue;

    @XmlElement(name = "margin")
    public String margin;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + settlementDate
                + ":" + settlementPeriod
                + ":" + nationalBoundaryIdentifier
                + ":" + publishingPeriodCommencingTime
                + ":" + imbalanceValue
                + ":" + margin
                + ":" + activeFlag
                ;
    }
}
