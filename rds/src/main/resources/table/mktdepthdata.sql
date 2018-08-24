CREATE TABLE bmrs.mktdepthdata(
    header_record_type              VARCHAR(255),
    header_file_type                VARCHAR(255),
    record_type                     VARCHAR(255),
    settlement_date                 DATE,
    settlement_period               INT,
    imbalance_value                 DECIMAL,
    ttl_ofr_vol                     DECIMAL,
    ttl_bid_vol                     DECIMAL,
    ttl_accepted_ofr_vol            DECIMAL,
    ttl_accepted_bid_vol            DECIMAL,
    ttl_unpriced_accepted_ofr_vol   DECIMAL,
    ttl_unpriced_accepted_bid_vol   DECIMAL,
    ttl_priced_accepted_ofr_vol     DECIMAL,
    ttl_priced_accepted_bid_vol     DECIMAL,
    active_flag                     VARCHAR(255)
);