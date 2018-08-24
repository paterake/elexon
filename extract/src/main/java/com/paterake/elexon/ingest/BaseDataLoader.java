package com.paterake.elexon.ingest;

class BaseDataLoader {
    static final String ELEXON_PORTAL_KEY = "xdzv1oef4vpevcz";
    static final String SERVICE_TYPE = "XML";
    static final String BUCKET_NAME = "bmrs";
    static final String FORMATTED_URL_SETTLEMENT_PERIOD = "https://api.bmreports.com/BMRS/%s/v1?ServiceType=%s&APIKey=%s&SettlementDate=%s&SettlementPeriod=%s";
    static final String FORMATTED_URL_FROM_TO = "https://api.bmreports.com/BMRS/%s/v1?ServiceType=%s&APIKey=%s&ZoneIdentifier=N&FromDate=%s&ToDate=%s";
    static final String FORMATTED_URL_EVENT_FROM_TO = "https://api.bmreports.com/BMRS/%s/v1?ServiceType=%s&APIKey=%s&EventStart=%s&EventEnd=%s";
    static final String FORMATTED_URL_NO_DATE = "https://api.bmreports.com/BMRS/%s/v1?ServiceType=%s&APIKey=%s";
    static final String FORMATTED_FILENAME = "%s-%s-%s.%s";
    static final String LOCAL_PATH = "c:/temp";
}
