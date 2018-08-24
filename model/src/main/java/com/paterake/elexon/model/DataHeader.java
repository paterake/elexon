package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Defines how an XML header in the BMRS reports should be bound to a Java Object.
 */
@XmlRootElement(name = "responseHeader")
public class DataHeader {
    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "fileType")
    public String fileType;

    @XmlElement(name = "settlementDate")
    public String settlementDate;

    @XmlElement(name = "settlementPeriod")
    public String settlementPeriod;


    @Override
    public String toString() {
        return recordType
                + ":" + fileType
                + ":" + settlementDate
                + ":" + settlementPeriod;
    }
}
