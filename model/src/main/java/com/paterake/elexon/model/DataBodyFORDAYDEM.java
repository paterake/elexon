package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyFORDAYDEM extends DataBody {

  @XmlElement(name = "nationalBoundaryIdentifier")
  public String nationalBoundaryIdentifier;

  @XmlElement(name = "settlementDate")
  public String settlementDate;

  @XmlElement(name = "settlementPeriod")
  public String settlementPeriod;

  @XmlElement(name = "recordType")
  public String recordType;

  @XmlElement(name = "publishingPeriodCommencingTime")
  public String publishingPeriodCommencingTime;

  @XmlElement(name = "demand")
  public String demand;

  @XmlElement(name = "spnDemand")
  public String spnDemand;

  @XmlElement(name = "spnGeneration")
  public String spnGeneration;

  @XmlElement(name = "activeFlag")
  public String activeFlag;

  @Override
  public String toString() {
    return nationalBoundaryIdentifier
        + ":" + settlementDate
        + ":" + settlementPeriod
        + ":" + recordType
        + ":" + publishingPeriodCommencingTime
        + ":" + demand
        + ":" + spnDemand
        + ":" + spnGeneration
        + ":" + activeFlag
        ;
  }
}
