package com.ghandreisv.meter.service;

import com.ghandreisv.meter.service.meterreading.MeterReadingRepository;
import com.ghandreisv.meter.service.report.MonthlyRecordProjection;
import com.ghandreisv.meter.service.report.MonthlyRecordProjectionFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    private MeterReadingRepository meterReadingRepository;

    @InjectMocks
    private ReportService reportService;

    private final Year year = Year.of(2021);
    private final Integer month = Month.JANUARY.getValue();
    private final MonthlyRecordProjection expectedRecord = MonthlyRecordProjectionFixture.withDateAndTotal(year.getValue(), month, 123L);


    @Test
    void getYearlyReport() {
        List<MonthlyRecordProjection> expected = Collections.singletonList(expectedRecord);
        when(meterReadingRepository.getYearlyRecords(year)).thenReturn(expected);

        List<MonthlyRecordProjection> yearlyReport = reportService.getYearlyReport(year);

        assertEquals(expected, yearlyReport);
        verify(meterReadingRepository).getYearlyRecords(year);
    }

    @Test
    void getMonthlyReport() {
        when(meterReadingRepository.getMonthlyRecord(YearMonth.of(year.getValue(), month)))
                .thenReturn(Optional.of(expectedRecord));

        Optional<MonthlyRecordProjection> monthlyReport = reportService.getMonthlyReport(year, Month.of(month));

        assertTrue(monthlyReport.isPresent());
        assertEquals(expectedRecord, monthlyReport.get());
        verify(meterReadingRepository).getMonthlyRecord(YearMonth.of(year.getValue(), month));
    }
}