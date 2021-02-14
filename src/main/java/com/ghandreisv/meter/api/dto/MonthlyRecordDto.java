package com.ghandreisv.meter.api.dto;

import lombok.Value;

import java.time.Month;

@Value
public class MonthlyRecordDto {
    Month month;
    Long value;
}
