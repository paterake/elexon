CREATE TABLE bmrs.histsyswarn(
    header_record_type              VARCHAR(255),
    header_file_type                VARCHAR(255),
    record_type                     VARCHAR(255),
    warning_type                    VARCHAR(255),
    warning_date_time               TIMESTAMP WITHOUT TIME ZONE,
    effective_from                  DATE,
    times_effective_from            TIME WITHOUT TIME ZONE,
    times_effective_to              TIME WITHOUT TIME ZONE,
    shortfall                       DECIMAL,
    date_warning_cancelled          DATE,
    time_warning_cancelled          TIME WITHOUT TIME ZONE,
    warning_text                    TEXT,
    active_flag                     VARCHAR(255)
);