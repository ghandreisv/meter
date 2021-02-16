package com.ghandreisv.meter.api.dto;

import lombok.Getter;

import java.time.Year;
import java.util.List;

@Getter
public class DetailedYearlyReportDto extends YearlyReportDto{
    private final List<MonthlyRecordDto> monthly;

    public DetailedYearlyReportDto(Year year, Long total, List<MonthlyRecordDto> monthly) {
        super(year, total);
        this.monthly = monthly;
    }
}
