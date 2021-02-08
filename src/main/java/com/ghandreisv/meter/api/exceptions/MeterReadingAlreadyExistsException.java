package com.ghandreisv.meter.api.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MeterReadingAlreadyExistsException extends RuntimeException {

    public MeterReadingAlreadyExistsException(String entityId, String entityType, LocalDate date) {
        super("A reading record for [" + entityType + "] with id '" + entityId + "' for date [" + date + "] already exists");
    }
}
