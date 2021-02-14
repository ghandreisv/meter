package com.ghandreisv.meter.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Year;


@AllArgsConstructor
@Getter
public class YearlyReportDto {
    private final Year year;
    private final Long total;
}
