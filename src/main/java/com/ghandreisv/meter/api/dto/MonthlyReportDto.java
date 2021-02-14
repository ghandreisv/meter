package com.ghandreisv.meter.api.dto;

import lombok.Value;

import java.time.Month;
import java.time.Year;

@Value
public class MonthlyReportDto {
    Year year;
    Month month;
    Long value;
}
