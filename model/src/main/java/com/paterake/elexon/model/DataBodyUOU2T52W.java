package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyUOU2T52W extends DataBody {

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "bmUnitName")
    public String bmUnitName;

    @XmlElement(name = "fuelType")
    public String fuelType;

    @XmlElement(name = "publishingPeriodCommencingTime")
    public String publishingPeriodCommencingTime;

    @XmlElement(name = "systemZone")
    public String systemZone;

    @XmlElement(name = "calendarWeekNumber")
    public String calendarWeekNumber;

    @XmlElement(name = "year")
    public String year;

    @XmlElement(name = "outputUsable")
    public String outputUsable;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString() {
        return recordType
                + ":" + bmUnitName
                + ":" + fuelType
                + ":" + publishingPeriodCommencingTime
                + ":" + systemZone
                + ":" + calendarWeekNumber
                + ":" + year
                + ":" + outputUsable
                + ":" + activeFlag
                ;
    }



}
