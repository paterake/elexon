package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyFUELINST extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "startTimeOfHalfHrPeriod")
    public String startTimeOfHalfHrPeriod;

    @XmlElement(name = "settlementPeriod")
    public String settlementPeriod;

    @XmlElement(name = "publishingPeriodCommencingTime")
    public String publishingPeriodCommencingTime;

    @XmlElement(name = "ccgt")
    public String ccgt;

    @XmlElement(name = "oil")
    public String oil;

    @XmlElement(name = "coal")
    public String coal;

    @XmlElement(name = "nuclear")
    public String nuclear;

    @XmlElement(name = "wind")
    public String wind;

    @XmlElement(name = "ps")
    public String ps;

    @XmlElement(name = "npshyd")
    public String npshyd;

    @XmlElement(name = "ocgt")
    public String ocgt;

    @XmlElement(name = "other")
    public String other;

    @XmlElement(name = "intfr")
    public String intfr;

    @XmlElement(name = "intirl")
    public String intirl;

    @XmlElement(name = "intned")
    public String intned;

    @XmlElement(name = "intew")
    public String intew;

    @XmlElement(name = "biomass")
    public String biomass;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString() {
        return recordType
                + ":" + startTimeOfHalfHrPeriod
                + ":" + settlementPeriod
                + ":" + publishingPeriodCommencingTime
                + ":" + ccgt
                + ":" + oil
                + ":" + coal
                + ":" + nuclear
                + ":" + wind
                + ":" + ps
                + ":" + npshyd
                + ":" + ocgt
                + ":" + other
                + ":" + intfr
                + ":" + intirl
                + ":" + intned
                + ":" + intew
                + ":" + biomass
                + ":" + activeFlag
                ;
    }
}
