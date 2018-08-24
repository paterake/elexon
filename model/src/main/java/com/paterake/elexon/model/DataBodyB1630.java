package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyB1630 extends DataBody {

    @XmlElement(name = "timeSeriesID")
    public String timeSeriesID;

    @XmlElement(name = "businessType")
    public String businessType;

    @XmlElement(name = "powerSystemResourceType")
    public String powerSystemResourceType;

    @XmlElement(name = "settlementDate")
    public String settlementDate;

    @XmlElement(name = "settlementPeriod")
    public String settlementPeriod;

    @XmlElement(name = "quantity")
    public String quantity;

    @XmlElement(name = "documentType")
    public String documentType;

    @XmlElement(name = "processType")
    public String processType;

    @XmlElement(name = "curveType")
    public String curveType;

    @XmlElement(name = "resolution")
    public String resolution;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @XmlElement(name = "documentID")
    public String documentID;

    @XmlElement(name = "documentRevNum")
    public String documentRevNum;

    @Override
    public String toString() {
        return timeSeriesID
                + ":" + businessType
                + ":" + powerSystemResourceType
                + ":" + settlementDate
                + ":" + settlementPeriod
                + ":" + quantity
                + ":" + documentType
                + ":" + processType
                + ":" + curveType
                + ":" + resolution
                + ":" + activeFlag
                + ":" + documentID
                + ":" + documentRevNum
                ;
    }
}
