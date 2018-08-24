package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "item")
public class DataBodyB1770 extends DataBody {

    @XmlElement(name = "timeSeriesID")
    public String timeSeriesID;

    @XmlElement(name = "businessType")
    public String businessType;

    @XmlElement(name = "controlArea")
    public String controlArea;

    @XmlElement(name = "settlementDate")
    public String settlementDate;

    @XmlElement(name = "settlementPeriod")
    public String settlementPeriod;

    @XmlElement(name = "imbalancePriceAmountGBP")
    public String imbalancePriceAmountGBP;

    @XmlElement(name = "priceCategory")
    public String priceCategory;

    @XmlElement(name = "curveType")
    public String curveType;

    @XmlElement(name = "resolution")
    public String resolution;

    @XmlElement(name = "documentType")
    public String documentType;

    @XmlElement(name = "processType")
    public String processType;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @XmlElement(name = "docStatus")
    public String docStatus;

    @XmlElement(name = "documentID")
    public String documentID;

    @XmlElement(name = "documentRevNum")
    public String documentRevNum;

    @Override
    public String toString() {
        return timeSeriesID
                + ":" + businessType
                + ":" + controlArea
                + ":" + settlementDate
                + ":" + settlementPeriod
                + ":" + imbalancePriceAmountGBP
                + ":" + priceCategory
                + ":" + curveType
                + ":" + resolution
                + ":" + documentType
                + ":" + processType
                + ":" + activeFlag
                + ":" + docStatus
                + ":" + documentID
                + ":" + documentRevNum
                ;
    }
}
