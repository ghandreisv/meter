package com.ghandreisv.meter.api.dto;

import lombok.Value;

import java.time.Month;
import java.util.Map;

@Value
public class MeterReadingsReportDto {
    Integer year;
    Long total;
    Map<Month, Long> monthly;

}
