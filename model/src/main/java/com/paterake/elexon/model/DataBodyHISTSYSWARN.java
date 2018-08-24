package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyHISTSYSWARN extends DataBody {

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "warningType")
    public String warningType;

    @XmlElement(name = "warningDateTime")
    public String warningDateTime;

    @XmlElement(name = "effectiveFrom")
    public String effectiveFrom;

    @XmlElement(name = "timesEffectiveFrom")
    public String timesEffectiveFrom;

    @XmlElement(name = "timesEffectiveTo")
    public String timesEffectiveTo;

    @XmlElement(name = "shortfall")
    public String shortfall;

    @XmlElement(name = "dateWarningCancelled")
    public String dateWarningCancelled;

    @XmlElement(name = "timeWarningCancelled")
    public String timeWarningCancelled;

    @XmlElement(name = "warningText")
    public String warningText;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + warningType
                + ":" + warningDateTime
                + ":" + effectiveFrom
                + ":" + timesEffectiveFrom
                + ":" + timesEffectiveTo
                + ":" + shortfall
                + ":" + dateWarningCancelled
                + ":" + timeWarningCancelled
                + ":" + warningText
                + ":" + activeFlag
                ;
    }
}
