package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyDEVINDOD extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "settlementDay")
    public String settlementDay;

    @XmlElement(name = "volume")
    public String volume;

    @XmlElement(name = "dailyEnergyVolumeNormalReference")
    public String dailyEnergyVolumeNormalReference;

    @XmlElement(name = "dailyEnergyVolumeLowReference")
    public String dailyEnergyVolumeLowReference;

    @XmlElement(name = "dailyEnergyVolumeHighReference")
    public String dailyEnergyVolumeHighReference;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString() {
        return recordType
                + ":" + settlementDay
                + ":" + volume
                + ":" + dailyEnergyVolumeNormalReference
                + ":" + dailyEnergyVolumeLowReference
                + ":" + dailyEnergyVolumeHighReference
                + ":" + activeFlag
                ;
    }




}
