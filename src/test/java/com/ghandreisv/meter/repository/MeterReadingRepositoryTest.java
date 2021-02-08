package com.ghandreisv.meter.repository;

import com.ghandreisv.meter.model.projection.MeterReadingId;
import com.ghandreisv.meter.model.projection.MonthlyReportProjection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MeterReadingRepositoryTest {

    @Autowired
    private MeterReadingRepository meterReadingRepository;

    @Test
    void findByClientAndDate() {
        String expectedReadingId = "77188b30-a5bb-4f63-b36c-20d2ec1bb911";
        Optional<MeterReadingId> meterReadingId = meterReadingRepository
                .findByClientAndDate("5d4a1603-5456-4340-9e1f-fb6861573570", LocalDate.of(2019, 1, 1));
        assertTrue(meterReadingId.isPresent());
        assertEquals(expectedReadingId, meterReadingId.get().getId());
    }

    @Test
    void findByAddressAndDate() {
        String expectedReadingId = "77188b30-a5bb-4f63-b36c-20d2ec1bb911";
        Optional<MeterReadingId> meterReadingId = meterReadingRepository
                .findByAddressAndDate("0ad46436-532d-4bfb-92e6-654e036b6cda", LocalDate.of(2019, 1, 1));
        assertTrue(meterReadingId.isPresent());
        assertEquals(expectedReadingId, meterReadingId.get().getId());
    }

    @Test
    void findByMeterIdAndDate() {
        String expectedReadingId = "77188b30-a5bb-4f63-b36c-20d2ec1bb911";
        Optional<MeterReadingId> meterReadingId = meterReadingRepository
                .findByMeterIdAndDate("4758677d-bb43-4f21-9bdf-98349b30003e", LocalDate.of(2019, 1, 1));
        assertTrue(meterReadingId.isPresent());
        assertEquals(expectedReadingId, meterReadingId.get().getId());
    }

    @Test
    void getMonthlyReport() {
        Optional<MonthlyReportProjection> monthlyReport = meterReadingRepository
                .getMonthlyReport(YearMonth.of(2019, 1));
        assertTrue(monthlyReport.isPresent());
        assertEquals(15, monthlyReport.get().getTotal());
        assertEquals(LocalDate.of(2019, 1, 1), monthlyReport.get().getDate());
    }

    @Test
    void getMonthlyReport_missing_record() {
        Optional<MonthlyReportProjection> monthlyReport = meterReadingRepository
                .getMonthlyReport(YearMonth.of(2020, 9));
        assertFalse(monthlyReport.isPresent());
    }

    @Test
    void getYearlyReport_2019() {
        testYearlyReport(2019, 105);
    }

    @Test
    void getYearlyReport_2020() {
        testYearlyReport(2020, 159);
    }

    @Test
    void getYearlyReport_1997() {
        testYearlyReport(1997, 0);
    }

    private void testYearlyReport(int year, long expectedTotal) {
        List<MonthlyReportProjection> yearlyReport = meterReadingRepository.getYearlyReport(Year.of(year));
        assertEquals(expectedTotal, yearlyReport.stream().mapToLong(MonthlyReportProjection::getTotal).sum());
    }
}