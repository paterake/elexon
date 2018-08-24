package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyDETSYSPRICES extends DataBody {

  @XmlElement(name = "recordType")
  public String recordType;

  @XmlElement(name = "settlementDate")
  public String settlementDate;

  @XmlElement(name = "settlementPeriod")
  public String settlementPeriod;

  @XmlElement(name = "index")
  public String index;

  @XmlElement(name = "id")
  public String id;

  @XmlElement(name = "acceptanceId")
  public String acceptanceId;

  @XmlElement(name = "bidOfferPairId")
  public String bidOfferPairId;

  @XmlElement(name = "cadlFlag")
  public String cadlFlag;

  @XmlElement(name = "soFlag")
  public String soFlag;

  @XmlElement(name = "storFlag")
  public String storFlag;

  @XmlElement(name = "repricedIndicator")
  public String repricedIndicator;

  @XmlElement(name = "reserveScarcityPrice")
  public String reserveScarcityPrice;

  @XmlElement(name = "offerPrice")
  public String offerPrice;

  @XmlElement(name = "offerVolume")
  public String offerVolume;

  @XmlElement(name = "bidPrice")
  public String bidPrice;

  @XmlElement(name = "bidVolume")
  public String bidVolume;

  @XmlElement(name = "dmatAdjustedVolume")
  public String dmatAdjustedVolume;

  @XmlElement(name = "arbitrageAdjustedVolume")
  public String arbitrageAdjustedVolume;

  @XmlElement(name = "nivAdjustedVolume")
  public String nivAdjustedVolume;

  @XmlElement(name = "parAdjustedvolume")
  public String parAdjustedvolume;

  @XmlElement(name = "finalPrice")
  public String finalPrice;

  @XmlElement(name = "transmissionLossMultiplier")
  public String transmissionLossMultiplier;

  @XmlElement(name = "tlmAdjustedVolume")
  public String tlmAdjustedVolume;

  @XmlElement(name = "tlmAdjustedCost")
  public String tlmAdjustedCost;

  @XmlElement(name = "totalOfTlmAdjustedVolume")
  public String totalOfTlmAdjustedVolume;

  @XmlElement(name = "totalOfTlmAdjustedCost")
  public String totalOfTlmAdjustedCost;

  @Override
  public String toString(){
    return recordType
        + ":" + settlementDate
        + ":" + settlementPeriod
        + ":" + index
        + ":" + id
        + ":" + acceptanceId
        + ":" + bidOfferPairId
        + ":" + cadlFlag
        + ":" + soFlag
        + ":" + storFlag
        + ":" + repricedIndicator
        + ":" + reserveScarcityPrice
        + ":" + offerPrice
        + ":" + offerVolume
        + ":" + bidPrice
        + ":" + bidVolume
        + ":" + dmatAdjustedVolume
        + ":" + arbitrageAdjustedVolume
        + ":" + nivAdjustedVolume
        + ":" + parAdjustedvolume
        + ":" + finalPrice
        + ":" + transmissionLossMultiplier
        + ":" + tlmAdjustedVolume
        + ":" + tlmAdjustedCost
        + ":" + totalOfTlmAdjustedVolume
        + ":" + totalOfTlmAdjustedCost;
  }
}
