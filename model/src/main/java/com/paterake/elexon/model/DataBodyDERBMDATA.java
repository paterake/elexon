package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyDERBMDATA extends DataBody {

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

  @XmlElement(name = "dataType")
  public String dataType;

  @XmlElement(name = "acceptanceID")
  public String acceptanceID;

  @XmlElement(name = "shortAcceptanceFlag")
  public String shortAcceptanceFlag;

  @XmlElement(name = "volumeAcceptedforBidOfferPair1")
  public String volumeAcceptedforBidOfferPair1;

  @XmlElement(name = "volumeAcceptedforBidOfferPair2")
  public String volumeAcceptedforBidOfferPair2;

  @XmlElement(name = "volumeAcceptedforBidOfferPair3")
  public String volumeAcceptedforBidOfferPair3;

  @XmlElement(name = "volumeAcceptedforBidOfferPair4")
  public String volumeAcceptedforBidOfferPair4;

  @XmlElement(name = "volumeAcceptedforBidOfferPair5")
  public String volumeAcceptedforBidOfferPair5;

  @XmlElement(name = "volumeAcceptedforBidOfferPair6")
  public String volumeAcceptedforBidOfferPair6;

  @XmlElement(name = "volumeAcceptedforBidOfferPair-1")
  public String volumeAcceptedforBidOfferPairMinus1;

  @XmlElement(name = "volumeAcceptedforBidOfferPair-2")
  public String volumeAcceptedforBidOfferPairMinus2;

  @XmlElement(name = "volumeAcceptedforBidOfferPair-3")
  public String volumeAcceptedforBidOfferPairMinus3;

  @XmlElement(name = "volumeAcceptedforBidOfferPair-4")
  public String volumeAcceptedforBidOfferPairMinus4;

  @XmlElement(name = "volumeAcceptedforBidOfferPair-5")
  public String volumeAcceptedforBidOfferPairMinus5;

  @XmlElement(name = "volumeAcceptedforBidOfferPair-6")
  public String volumeAcceptedforBidOfferPairMinus6;

  @XmlElement(name = "cashFlowforBidOfferPair1")
  public String cashFlowforBidOfferPair1;

  @XmlElement(name = "cashFlowforBidOfferPair2")
  public String cashFlowforBidOfferPair2;

  @XmlElement(name = "cashFlowforBidOfferPair3")
  public String cashFlowforBidOfferPair3;

  @XmlElement(name = "cashFlowforBidOfferPair4")
  public String cashFlowforBidOfferPair4;

  @XmlElement(name = "cashFlowforBidOfferPair5")
  public String cashFlowforBidOfferPair5;

  @XmlElement(name = "cashFlowforBidOfferPair6")
  public String cashFlowforBidOfferPair6;

  @XmlElement(name = "cashFlowforBidOfferPair-1")
  public String cashFlowforBidOfferPairMinus1;

  @XmlElement(name = "cashFlowforBidOfferPair-2")
  public String cashFlowforBidOfferPairMinus2;

  @XmlElement(name = "cashFlowforBidOfferPair-3")
  public String cashFlowforBidOfferPairMinus3;

  @XmlElement(name = "cashFlowforBidOfferPair-4")
  public String cashFlowforBidOfferPairMinus4;

  @XmlElement(name = "cashFlowforBidOfferPair-5")
  public String cashFlowforBidOfferPairMinus5;

  @XmlElement(name = "cashFlowforBidOfferPair-6")
  public String cashFlowforBidOfferPairMinus6;

  @XmlElement(name = "total")
  public String total;

  @XmlElement(name = "activeFlag")
  public String activeFlag;

}
