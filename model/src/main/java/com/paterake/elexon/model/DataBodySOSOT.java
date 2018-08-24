package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodySOSOT extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "warningDateTime")
    public String warningDateTime;

    @XmlElement(name = "messageText")
    public String messageText;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + warningDateTime
                + ":" + messageText
                + ":" + activeFlag
                ;
    }
}
