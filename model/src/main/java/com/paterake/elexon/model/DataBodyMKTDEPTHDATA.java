package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyMKTDEPTHDATA extends DataBody {

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "settlementDate")
    public String settlementDate;

    @XmlElement(name = "settlementPeriod")
    public String settlementPeriod;

    @XmlElement(name = "imbalanceValue")
    public String imbalanceValue;

    @XmlElement(name = "totalOfferVolume")
    public String totalOfferVolume;

    @XmlElement(name = "totalBidVolume")
    public String totalBidVolume;

    @XmlElement(name = "totalAcceptedOfferVolume")
    public String totalAcceptedOfferVolume;

    @XmlElement(name = "totalAcceptedBidVolume")
    public String totalAcceptedBidVolume;

    @XmlElement(name = "totalUnpricedAcceptedOfferVolume")
    public String totalUnpricedAccceptedOfferVolume;

    @XmlElement(name = "totalUnpricedAcceptedBidVolume")
    public String totalUnpricedAcceptedBidVolume;

    @XmlElement(name = "totalPricedAcceptedOfferVolume")
    public String totalPricedAcceptedOfferVolume;

    @XmlElement(name = "totalPricedAcceptedBidVolume")
    public String totalPricedAcceptedBidVolume;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + settlementDate
                + ":" + settlementPeriod
                + ":" + imbalanceValue
                + ":" + totalOfferVolume
                + ":" + totalBidVolume
                + ":" + totalAcceptedOfferVolume
                + ":" + totalAcceptedBidVolume
                + ":" + totalUnpricedAccceptedOfferVolume
                + ":" + totalUnpricedAcceptedBidVolume
                + ":" + totalPricedAcceptedOfferVolume
                + ":" + totalPricedAcceptedBidVolume
                + ":" + activeFlag
                ;
    }

}
