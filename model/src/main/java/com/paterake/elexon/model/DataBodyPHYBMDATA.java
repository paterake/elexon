package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyPHYBMDATA extends DataBody {

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

  @XmlElement(name = "timeFrom")
  public String timeFrom;

  @XmlElement(name = "pnLevelFrom")
  public String pnLevelFrom;

  @XmlElement(name = "melLevelFrom")
  public String melLevelFrom;

  @XmlElement(name = "timeTo")
  public String timeTo;

  @XmlElement(name = "pnLevelTo")
  public String pnLevelTo;

  @XmlElement(name = "melLevelTo")
  public String melLevelTo;

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
        + ":" + timeFrom
        + ":" + pnLevelFrom
        + ":" + timeTo
        + ":" + pnLevelTo
        + ":" + activeFlag
        ;
  }
}
