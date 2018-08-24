package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodySYSWARN extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "warningDateTime")
    public String warningDateTime;

    @XmlElement(name = "warningText")
    public String warningText;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString() {
        return recordType
                + ":" + warningDateTime
                + ":" + warningText
                + ":" + activeFlag
                ;
    }
}
