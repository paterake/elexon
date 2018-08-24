package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyDYNBMDATA extends DataBody {

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "bmUnitID")
    public String bmUnitID;

    @XmlElement(name = "bMUnitType")
    public String bMUnitType;

    @XmlElement(name = "leadPartyName")
    public String leadPartyName;

    @XmlElement(name = "ngcBMUnitName")
    public String ngcBMUnitName;

    @XmlElement(name = "settlementDate")
    public String settlementDate;

    @XmlElement(name = "settlementPeriod")
    public String settlementPeriod;

    @XmlElement(name = "effectiveTime")
    public String effectiveTime;

    @XmlElement(name = "runUpRate1")
    public String runUpRate1;

    @XmlElement(name = "runUpElbow2")
    public String runUpElbow2;

    @XmlElement(name = "runUpRate2")
    public String runUpRate2;

    @XmlElement(name = "runUpElbow3")
    public String runUpElbow3;

    @XmlElement(name = "runUpRate3")
    public String runUpRate3;

    @XmlElement(name = "runDownRate1")
    public String runDownRate1;

    @XmlElement(name = "runDownElbow2")
    public String runDownElbow2;

    @XmlElement(name = "runDownRate2")
    public String runDownRate2;

    @XmlElement(name = "runDownElbow3")
    public String runDownElbow3;

    @XmlElement(name = "runDownRate3")
    public String runDownRate3;

    @XmlElement(name = "noticeToDeviateFromZero")
    public String noticeToDeviateFromZero;

    @XmlElement(name = "noticeToDeliverBids")
    public String noticeToDeliverBids;

    @XmlElement(name = "noticeToDeliverOffers")
    public String noticeToDeliverOffers;

    @XmlElement(name = "minimumZeroTime")
    public String minimumZeroTime;

    @XmlElement(name = "minimumNonZeroTime")
    public String minimumNonZeroTime;

    @XmlElement(name = "stableExportLimit")
    public String stableExportLimit;

    @XmlElement(name = "stableImportLimit")
    public String stableImportLimit;

    @XmlElement(name = "maximumDeliveryVolume")
    public String maximumDeliveryVolume;

    @XmlElement(name = "maximumDeliveryPeriod")
    public String maximumDeliveryPeriod;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString() {
        return recordType
                + ":" + bmUnitID
                + ":" + bMUnitType
                + ":" + leadPartyName
                + ":" + ngcBMUnitName
                + ":" + settlementDate
                + ":" + settlementPeriod
                + ":" + effectiveTime
                + ":" + runUpRate1
                + ":" + runUpElbow2
                + ":" + runUpRate2
                + ":" + runUpElbow3
                + ":" + runUpRate3
                + ":" + runDownRate1
                + ":" + runDownElbow2
                + ":" + runDownRate2
                + ":" + runDownElbow3
                + ":" + runDownRate3
                + ":" + noticeToDeviateFromZero
                + ":" + noticeToDeliverBids
                + ":" + noticeToDeliverOffers
                + ":" + minimumZeroTime
                + ":" + minimumNonZeroTime
                + ":" + stableExportLimit
                + ":" + stableImportLimit
                + ":" + maximumDeliveryVolume
                + ":" + maximumDeliveryPeriod
                + ":" + activeFlag
                ;
    }
}
