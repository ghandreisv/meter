package com.ghandreisv.meter.api.reports;

import com.ghandreisv.meter.api.dto.DetailedYearlyReportDto;
import com.ghandreisv.meter.api.dto.MonthlyRecordDto;
import com.ghandreisv.meter.api.dto.YearlyReportDto;
import com.ghandreisv.meter.model.MonthlyRecordProjection;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DetailedYearlyReportCreator extends YearlyReportCreator {

    private static final Map<Month, MonthlyRecordDto> EMPTY_MONTHLY_TOTALS = Arrays.stream(Month.values())
            .collect(Collectors.toMap(m -> m, (m -> new MonthlyRecordDto(m, 0L))));

    public DetailedYearlyReportDto createReport(Year year, List<MonthlyRecordProjection> records) {
        YearlyReportDto aggregatedReport = super.createReport(year, records);

        return new DetailedYearlyReportDto(aggregatedReport.getYear(),
                aggregatedReport.getTotal(),
                createMonthlyRecordsDto(records));
    }

    protected List<MonthlyRecordDto> createMonthlyRecordsDto(List<MonthlyRecordProjection> records) {
        Map<Month, MonthlyRecordDto> monthlyRecords = records.stream()
                .map(p -> new MonthlyRecordDto(p.getMonth(), p.getValue()))
                .collect(Collectors.toMap(MonthlyRecordDto::getMonth, r -> r));
        Collection<MonthlyRecordDto> monthlyRecordDtoMap = Stream.of(monthlyRecords, EMPTY_MONTHLY_TOTALS)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1))
                .values();
        List<MonthlyRecordDto> result = new ArrayList<>(monthlyRecordDtoMap);
        result.sort(Comparator.comparingInt(o -> o.getMonth().getValue()));

        return result;
    }

}
