package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyTEMP extends DataBody{
    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "publishingPeriodCommencingTime")
    public String publishingPeriodCommencingTime;

    @XmlElement(name = "temperature")
    public String temperature;

    @XmlElement(name = "normalReferenceTemperature")
    public String normalReferenceTemperature;

    @XmlElement(name = "lowReferenceTemperature")
    public String lowReferenceTemperature;

    @XmlElement(name = "highReferenceTemperature")
    public String highReferenceTemperature;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString() {
        return recordType
                + ":" + publishingPeriodCommencingTime
                + ":" + temperature
                + ":" + normalReferenceTemperature
                + ":" + lowReferenceTemperature
                + ":" + highReferenceTemperature
                + ":" + activeFlag
                ;
    }




}
