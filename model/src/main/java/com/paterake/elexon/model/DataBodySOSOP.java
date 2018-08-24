package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodySOSOP extends DataBody{

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "tradeType")
    public String tradeType;

    @XmlElement(name = "startTime")
    public String startTime;

    @XmlElement(name = "date")
    public String date;

    @XmlElement(name = "tradeDirection")
    public String tradeDirection;

    @XmlElement(name = "contractIdentification")
    public String contractIdentification;

    @XmlElement(name = "tradeQuantity")
    public String tradeQuantity;

    @XmlElement(name = "tradePrice")
    public String tradePrice;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + tradeType
                + ":" + startTime
                + ":" + date
                + ":" + tradeDirection
                + ":" + contractIdentification
                + ":" + tradeQuantity
                + ":" + tradePrice
                + ":" + activeFlag
                ;
    }
}

