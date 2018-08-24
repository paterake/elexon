package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyDEMMF2T14D extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "dayOfForecast")
    public String dayOfForecast;

    @XmlElement(name = "startTimeOfHalfHrPeriod")
    public String startTimeOfHalfHrPeriod;

    @XmlElement(name = "boundaryID")
    public String boundaryID;

    @XmlElement(name = "publishingPeriodCommencingTime")
    public String publishingPeriodCommencingTime;

    @XmlElement(name = "demand")
    public String demand;

    @XmlElement(name = "margin")
    public String margin;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + dayOfForecast
                + ":" + startTimeOfHalfHrPeriod
                + ":" + boundaryID
                + ":" + publishingPeriodCommencingTime
                + ":" + demand
                + ":" + margin
                + ":" + activeFlag
                ;
    }

}
