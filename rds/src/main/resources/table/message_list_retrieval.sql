CREATE TABLE bmrs.message_list_retrieval (
  message_id                        VARCHAR(255)
, sequence_id                       INT
, message_heading                   VARCHAR(255)
, event_type                        VARCHAR(255)
, published_datetime                TIMESTAMP WITHOUT TIME ZONE
, participant_market_participant_id VARCHAR(255)
, asset_id                          VARCHAR(255)
, asset_eic_code                    VARCHAR(255)
, asset_type                        VARCHAR(255)
, affected_unit                     VARCHAR(255)
, affected_area                     VARCHAR(255)
, asset_fuel_type                   VARCHAR(255)
, asset_normal_capacity             DECIMAL
, available_capacity                DECIMAL
, event_start                       TIMESTAMP WITHOUT TIME ZONE
, event_end                         TIMESTAMP WITHOUT TIME ZONE
, duration_uncertainty              VARCHAR(255)
, cause                             VARCHAR(255)
, event_status                      VARCHAR(255)
, related_information               VARCHAR(255)
, active_flag                       VARCHAR(255)
, revision_number                   INT
, message_type                      VARCHAR(255)
, unavailability_type               VARCHAR(255)
, unavailable_capacity              DECIMAL
);
