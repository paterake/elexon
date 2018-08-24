package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyFUELINSTHHCUR extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "fuelType")
    public String fuelType;

    @XmlElement(name = "currentMW")
    public String currentMW;

    @XmlElement(name = "currentPercentage")
    public String currentPercentage;

    @XmlElement(name = "lastHalfHourLocalStartTime")
    public String lastHalfHourLocalStartTime;

    @XmlElement(name = "lastHalfHourLocalEndTime")
    public String lastHalfHourLocalEndTime;

    @XmlElement(name = "lastHalfHourMW")
    public String lastHalfHourMW;

    @XmlElement(name = "lastHalfHourPercentage")
    public String lastHalfHourPercentage;

    @XmlElement(name = "last24HourLocalStartTime")
    public String last24HourLocalStartTime;

    @XmlElement(name = "last24HourLocalEndTime")
    public String last24HourLocalEndTime;

    @XmlElement(name = "last24HourMWh")
    public String last24HourMWh;

    @XmlElement(name = "last24HourPercentage")
    public String last24HourPercentage;

    @XmlElement(name = "activeFlag")
    public String activeFlag;



    @Override
    public String toString(){
        return recordType
                + ":" + fuelType
                + ":" + currentMW
                + ":" + currentPercentage
                + ":" + lastHalfHourLocalStartTime
                + ":" + lastHalfHourLocalEndTime
                + ":" + lastHalfHourMW
                + ":" + lastHalfHourPercentage
                + ":" + last24HourLocalStartTime
                + ":" + last24HourLocalEndTime
                + ":" + last24HourMWh
                + ":" + last24HourPercentage
                + ":" + activeFlag
               ;
    }









}
