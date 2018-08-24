CREATE TABLE bmrs.lolpdrm(
    header_record_type          VARCHAR(255),
    header_file_type            VARCHAR(255),
    record_type                 VARCHAR(255),
    settlement_date             DATE,
    settlement_period           INT,
    lolp_12_hour_forecast       DECIMAL,
    drm_12_hour_forecast        DECIMAL,
    lolp_8_hour_forecast        DECIMAL,
    drm_8_hour_forecast         DECIMAL,
    lolp_4_hour_forecast        DECIMAL,
    drm_4_hour_forecast         DECIMAL,
    lolp_2_hour_forecast        DECIMAL,
    drm_2_hour_forecast         DECIMAL,
    lolp_1_hour_forecast        DECIMAL,
    drm_1_hour_forecast         DECIMAL,
    active_flag                 VARCHAR(255)
);