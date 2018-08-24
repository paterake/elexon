package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyPKDEMYESTTDYTOM extends DataBody {

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "date")
    public String date;

    @XmlElement(name = "tsdfDemand")
    public String tsdfDemand;

    @XmlElement(name = "tsdfStartTimeOfHalfHrPeriod")
    public String tsdfStartTimeOfHalfHrPeriod;

    @XmlElement(name = "itsdoDemand")
    public String itsdoDemand;

    @XmlElement(name = "itsdoStartTimeOfHalfHrPeriod")
    public String itsdoStartTimeOfHalfHrPeriod;

    @XmlElement(name = "publishingPeriodCommencingTime")
    public String publishingPeriodCommencingTime;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + date
                + ":" + tsdfDemand
                + ":" + tsdfStartTimeOfHalfHrPeriod
                + ":" + itsdoDemand
                + ":" + itsdoStartTimeOfHalfHrPeriod
                + ":" + publishingPeriodCommencingTime
                + ":" + activeFlag
                ;
    }
}
