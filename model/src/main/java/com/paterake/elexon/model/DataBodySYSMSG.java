package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodySYSMSG extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "messageDateTime")
    public String messageDateTime;

    @XmlElement(name = "messageType")
    public String messageType;

    @XmlElement(name = "messageText")
    public String messageText;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + messageDateTime
                + ":" + messageType
                + ":" + messageText
                + ":" + activeFlag
                ;
    }
}
