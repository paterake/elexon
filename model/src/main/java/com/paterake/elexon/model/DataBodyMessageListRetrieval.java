package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyMessageListRetrieval extends DataBody {

  @XmlElement(name = "messageID")
  public String messageID;

  @XmlElement(name = "sequenceID")
  public String sequenceID;

  @XmlElement(name = "messageHeading")
  public String messageHeading;

  @XmlElement(name = "eventType")
  public String eventType;

  @XmlElement(name = "publishedDateTime")
  public String publishedDateTime;

  @XmlElement(name = "participant_MarketParticipantID")
  public String participant_MarketParticipantID;

  @XmlElement(name = "assetID")
  public String assetID;

  @XmlElement(name = "assetEICCode")
  public String assetEICCode;

  @XmlElement(name = "assetType")
  public String assetType;

  @XmlElement(name = "affectedUnit")
  public String affectedUnit;

  @XmlElement(name = "affectedArea")
  public String affectedArea;

  @XmlElement(name = "assetFuelType")
  public String assetFuelType;

  @XmlElement(name = "assetNormalCapacity")
  public String assetNormalCapacity;

  @XmlElement(name = "availableCapacity")
  public String availableCapacity;

  @XmlElement(name = "eventStart")
  public String eventStart;

  @XmlElement(name = "eventEnd")
  public String eventEnd;

  @XmlElement(name = "durationUncertainty")
  public String durationUncertainty;

  @XmlElement(name = "cause")
  public String cause;

  @XmlElement(name = "eventStatus")
  public String eventStatus;

  @XmlElement(name = "relatedInformation")
  public String relatedInformation;

  @XmlElement(name = "activeFlag")
  public String activeFlag;

  @XmlElement(name = "revisionNumber")
  public String revisionNumber;

  @XmlElement(name = "messageType")
  public String messageType;

  @XmlElement(name = "unavailabilityType")
  public String unavailabilityType;

  @XmlElement(name = "unavailableCapacity")
  public String unavailableCapacity;

  @Override
  public String toString() {
    return messageID
        + ":" + sequenceID
        + ":" + messageHeading
        + ":" + eventType
        + ":" + publishedDateTime
        + ":" + participant_MarketParticipantID
        + ":" + assetID
        + ":" + assetEICCode
        + ":" + assetType
        + ":" + affectedUnit
        + ":" + affectedArea
        + ":" + assetFuelType
        + ":" + assetNormalCapacity
        + ":" + availableCapacity
        + ":" + eventStart
        + ":" + eventEnd
        + ":" + durationUncertainty
        + ":" + cause
        + ":" + eventStatus
        + ":" + relatedInformation
        + ":" + activeFlag
        + ":" + revisionNumber
        + ":" + messageType
        + ":" + unavailabilityType
        + ":" + unavailableCapacity
        ;
  }
}

