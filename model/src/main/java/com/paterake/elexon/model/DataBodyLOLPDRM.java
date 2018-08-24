package com.paterake.elexon.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
public class DataBodyLOLPDRM extends DataBody {

    @XmlElement(name = "recordType")
    public String recordType;

    @XmlElement(name = "settlementDate")
    public String settlementDate;

    @XmlElement(name = "settlementPeriod")
    public String settlementPeriod;

    @XmlElement(name = "lolp12Forecast")
    public String lolp12Forecast;

    @XmlElement(name = "drm12Forecast")
    public String drm12Forecast;

    @XmlElement(name = "lolp8HourForecast")
    public String lolp8HourForecast;

    @XmlElement(name = "drm8HourForecast")
    public String drm8HourForecast;

    @XmlElement(name = "lolp4HourForecast")
    public String lolp4HourForecast;

    @XmlElement(name = "drm4HourForecast")
    public String drm4HourForecast;

    @XmlElement(name = "lolp2HourForecast")
    public String lolp2HourForecast;

    @XmlElement(name = "drm2HourForecast")
    public String drm2HourForecast;

    @XmlElement(name = "lolp1HourForecast")
    public String lolp1HourForecast;

    @XmlElement(name = "drm1HourForecast")
    public String drm1HourForecast;

    @XmlElement(name = "activeFlag")
    public String activeFlag;

    @Override
    public String toString(){
        return recordType
                + ":" + settlementDate
                + ":" + settlementPeriod
                + ":" + lolp12Forecast
                + ":" + drm12Forecast
                + ":" + lolp8HourForecast
                + ":" + drm8HourForecast
                + ":" + lolp4HourForecast
                + ":" + drm4HourForecast
                + ":" + lolp2HourForecast
                + ":" + drm2HourForecast
                + ":" + lolp1HourForecast
                + ":" + drm1HourForecast
                + ":" + activeFlag
                ;
    }
}
