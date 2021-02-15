CREATE TABLE ADDRESSES
(
    id           VARCHAR(36) PRIMARY KEY,
    street       VARCHAR(128),
    house_number VARCHAR(32),
    city         VARCHAR(64),
    country      VARCHAR(64)
);

CREATE TABLE CLIENTS
(
    id         VARCHAR(36) PRIMARY KEY,
    first_name VARCHAR(64),
    last_name  VARCHAR(64),
    address_id VARCHAR(36) NOT NULL,
    CONSTRAINT UQ_CLIENTS_ADDRESS UNIQUE (address_id),
    CONSTRAINT FK_CLIENTS_ADDRESS_ID FOREIGN KEY (address_id)
        REFERENCES ADDRESSES (id)
);

CREATE TABLE METERS
(
    id         VARCHAR(36) PRIMARY KEY,
    address_id VARCHAR(36) NOT NULL,
    CONSTRAINT UQ_METERS_ADDRESS UNIQUE (address_id),
    CONSTRAINT FK_METERS_ADDRESS_ID FOREIGN KEY (address_id)
        REFERENCES ADDRESSES (id)
);

CREATE TABLE METER_READINGS
(
    id         VARCHAR(36) PRIMARY KEY,
    meter_id   VARCHAR(36) NOT NULL,
    date       DATE        NOT NULL,
    year_month DATE AS DATEADD(DAY, - DAY (date) + 1, date),
    value      NUMBER      NOT NULL,
    CONSTRAINT UQ_METER_YEAR_MONTH UNIQUE (meter_id, year_month)
);