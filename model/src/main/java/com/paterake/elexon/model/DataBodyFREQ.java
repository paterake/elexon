package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyFREQ extends DataBody {

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "reportSnapshotTime")
    public String reportSnapshotTime;

    @XmlElement(name = "spotTime")
    public String spotTime;

    @XmlElement(name = "frequency")
    public String frequency;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString() {
        return recordType
                + ":" + reportSnapshotTime
                + ":" + spotTime
                + ":" + frequency
                + ":" + activeFlag
                ;
    }

}
