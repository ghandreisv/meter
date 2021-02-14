package com.ghandreisv.meter.api.reports;

import com.ghandreisv.meter.api.dto.DetailedYearlyReportDto;
import com.ghandreisv.meter.api.dto.MonthlyRecordDto;
import com.ghandreisv.meter.model.projection.MonthlyRecordProjection;
import com.ghandreisv.meter.model.projection.MonthlyRecordProjectionFixture;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DetailedYearlyReportCreatorTest {

    private final DetailedYearlyReportCreator reportCreator = new DetailedYearlyReportCreator();

    @Test
    public void createEmptyReport() {
        List<MonthlyRecordProjection> records = Collections.emptyList();

        DetailedYearlyReportDto report = reportCreator.createReport(Year.of(2021), records);

        assertEquals(0, report.getTotal());
        assertEquals(Month.values().length, report.getMonthly().size());
        assertTrue(report.getMonthly().stream().allMatch(v -> v.getValue() == 0L));
    }

    @Test
    public void createNonEmptyReport() {
        LocalDate monthDate = LocalDate.of(2021, 1, 1);
        MonthlyRecordDto expected1 = new MonthlyRecordDto(monthDate.getMonth(), 777L);
        LocalDate monthDate2 = LocalDate.of(2021, 2, 1);
        MonthlyRecordDto expected2 = new MonthlyRecordDto(monthDate2.getMonth(), 999L);
        List<MonthlyRecordProjection> records = Arrays.asList(
                MonthlyRecordProjectionFixture.withDateAndTotal(monthDate, expected1.getValue()),
                MonthlyRecordProjectionFixture.withDateAndTotal(monthDate2, expected2.getValue())
        );

        DetailedYearlyReportDto report = reportCreator.createReport(Year.of(2021), records);

        assertEquals(expected1.getValue() + expected2.getValue(), report.getTotal());
        assertEquals(Month.values().length, report.getMonthly().size());
        assertEquals(expected1, report.getMonthly().get(0));
        assertEquals(expected2, report.getMonthly().get(1));
    }

}