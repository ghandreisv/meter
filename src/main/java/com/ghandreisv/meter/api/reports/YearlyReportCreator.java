package com.ghandreisv.meter.api.reports;

import com.ghandreisv.meter.api.dto.YearlyReportDto;
import com.ghandreisv.meter.model.projection.MonthlyRecordProjection;
import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.List;

@Component
public class YearlyReportCreator {

    public YearlyReportDto createReport(Year year, List<MonthlyRecordProjection> records) {
        Long total = records.stream().mapToLong(MonthlyRecordProjection::getTotal).sum();
        return new YearlyReportDto(year, total);
    }
}
