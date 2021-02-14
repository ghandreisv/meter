package com.ghandreisv.meter.api.exceptions;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MeterReadingAlreadyExistsException extends RuntimeException {

    public MeterReadingAlreadyExistsException(String entityId, String entityType, LocalDate date) {
        super("A reading record identified by [" + entityType + ":" + entityId + "] for month [" + YearMonth.from(date) + "] already exists");
    }
}
