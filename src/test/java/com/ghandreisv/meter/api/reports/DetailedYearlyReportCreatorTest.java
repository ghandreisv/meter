package com.ghandreisv.meter.api.reports;

import com.ghandreisv.meter.api.dto.DetailedYearlyReportDto;
import com.ghandreisv.meter.api.dto.MonthlyRecordDto;
import com.ghandreisv.meter.service.report.MonthlyRecordProjection;
import com.ghandreisv.meter.service.report.MonthlyRecordProjectionFixture;
import org.junit.jupiter.api.Test;

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
        int year = 2021;
        Month month1 = Month.JANUARY;
        MonthlyRecordDto expected1 = new MonthlyRecordDto(month1, 777L);
        Month month2 = Month.FEBRUARY;
        MonthlyRecordDto expected2 = new MonthlyRecordDto(month2, 999L);
        List<MonthlyRecordProjection> records = Arrays.asList(
                MonthlyRecordProjectionFixture.withDateAndTotal(year, month1.getValue(), expected1.getValue()),
                MonthlyRecordProjectionFixture.withDateAndTotal(year, month2.getValue(), expected2.getValue())
        );

        DetailedYearlyReportDto report = reportCreator.createReport(Year.of(2021), records);

        assertEquals(expected1.getValue() + expected2.getValue(), report.getTotal());
        assertEquals(Month.values().length, report.getMonthly().size());
        assertEquals(expected1, report.getMonthly().get(0));
        assertEquals(expected2, report.getMonthly().get(1));
    }

}