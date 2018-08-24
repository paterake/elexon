package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyINDPKDEMINFO extends DataBody {

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "startTimeOfHalfHrPeriod")
    public String startTimeOfHalfHrPeriod;

    @XmlElement(name = "calendarWeekNumber")
    public String calendarWeekNumber;

    @XmlElement(name = "demand")
    public String demand;

    @XmlElement(name = "timeOfPeak")
    public String timeOfPeak;

    @Override
    public String toString(){
        return recordType
                + ":" + startTimeOfHalfHrPeriod
                + ":" + calendarWeekNumber
                + ":" + demand
                + ":" + timeOfPeak
                ;
    }

}
