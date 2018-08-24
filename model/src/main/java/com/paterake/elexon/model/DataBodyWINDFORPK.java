package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyWINDFORPK extends DataBody {

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "dayAndDate")
    public String dayAndDate;

    @XmlElement(name = "startTimeOfHalfHrPeriod")
    public String startTimeOfHalfHrPeriod;

    @XmlElement(name = "peakMaxGeneration")
    public String peakMaxGeneration;

    @XmlElement(name = "totalMeteredCapacity")
    public String totalMeteredCapacity;

    @XmlElement(name = "dataLastUpdated")
    public String dataLastUpdated;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + dayAndDate
                + ":" + startTimeOfHalfHrPeriod
                + ":" + peakMaxGeneration
                + ":" + totalMeteredCapacity
                + ":" + dataLastUpdated
                + ":" + activeFlag
                ;
    }
}
