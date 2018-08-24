package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyWINDFORFUELHH extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "startTimeOfHalfHrPeriod")
    public String startTimeOfHalfHrPeriod;

    @XmlElement(name = "settlementPeriod")
    public String settlementPeriod;

    @XmlElement(name = "initialForecastPublishingPeriodCommencingTime")
    public String initialForecastPublishingPeriodCommencingTime;

    @XmlElement(name = "initialForecastSpnGeneration")
    public String initialForecastSpnGeneration;

    @XmlElement(name = "latestForecastPublishingPeriodCommencingTime")
    public String latestForecastPublishingPeriodCommencingTime;

    @XmlElement(name = "latestForecastSpnGeneration")
    public String latestForecastSpnGeneration;

    @XmlElement(name = "outTurnPublishingPeriodCommencingTime")
    public String outTurnPublishingPeriodCommencingTime;

    @XmlElement(name = "fuelTypeGeneration")
    public String fuelTypeGeneration;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString() {
        return recordType
                + ":" + startTimeOfHalfHrPeriod
                + ":" + settlementPeriod
                + ":" + initialForecastPublishingPeriodCommencingTime
                + ":" + initialForecastSpnGeneration
                + ":" + latestForecastPublishingPeriodCommencingTime
                + ":" + latestForecastSpnGeneration
                + ":" + outTurnPublishingPeriodCommencingTime
                + ":" + fuelTypeGeneration
                + ":" + activeFlag
                ;
    }
}
