--TODO: add FK
CREATE TABLE CLIENTS
(
    id         VARCHAR(36) PRIMARY KEY,
    first_name VARCHAR(64),
    last_name  VARCHAR(64),
    address_id VARCHAR(36)
);

CREATE TABLE ADDRESSES
(
    id           VARCHAR(36) PRIMARY KEY,
    street       VARCHAR(128),
    house_number VARCHAR(32),
    city         VARCHAR(64),
    country      VARCHAR(64)
);

CREATE TABLE METERS
(
    id VARCHAR(36) PRIMARY KEY
);

CREATE TABLE ADDRESSES_METERS
(
    address_id VARCHAR(36),
    meter_id   VARCHAR(36),
    CONSTRAINT PK_ADDRESS_METER PRIMARY KEY (address_id, meter_id)
);

CREATE TABLE METER_READINGS
(
    id       VARCHAR(36) PRIMARY KEY,
    meter_id VARCHAR(36),
    date     DATE,
    value    NUMBER,
    CONSTRAINT UQ_METER_DATE UNIQUE (meter_id, date)
);