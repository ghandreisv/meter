package com.ghandreisv.meter.repository;

import com.ghandreisv.meter.model.MonthlyRecordProjection;
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
    void existsByClientAndDate() {
        boolean exists = meterReadingRepository
                .existsByClientAndDate("5d4a1603-5456-4340-9e1f-fb6861573570", LocalDate.of(2019, 1, 1));
        assertTrue(exists);
    }

    @Test
    void existsByClientAndDate_not_found() {
        boolean exists = meterReadingRepository
                .existsByClientAndDate("not existing id", LocalDate.of(2019, 1, 1));
        assertFalse(exists);
    }

    @Test
    void existsByAddressAndDate() {
        boolean exists = meterReadingRepository
                .existsByAddressAndDate("0ad46436-532d-4bfb-92e6-654e036b6cda", LocalDate.of(2019, 1, 1));
        assertTrue(exists);
    }

    @Test
    void existsByAddressAndDate_not_found() {
        boolean exists = meterReadingRepository
                .existsByAddressAndDate("not existing id", LocalDate.of(2019, 1, 1));
        assertFalse(exists);
    }

    @Test
    void existsByMeterIdAndDate() {
        boolean exists = meterReadingRepository
                .existsByMeterIdAndDate("4758677d-bb43-4f21-9bdf-98349b30003e", LocalDate.of(2019, 1, 1));
        assertTrue(exists);
    }

    @Test
    void existsByMeterIdAndDate_not_found() {
        boolean exists = meterReadingRepository
                .existsByMeterIdAndDate("not existing id", LocalDate.of(2019, 1, 1));
        assertFalse(exists);
    }

    @Test
    void getMonthlyReport() {
        Optional<MonthlyRecordProjection> monthlyReport = meterReadingRepository
                .getMonthlyRecord(YearMonth.of(2019, 1));
        assertTrue(monthlyReport.isPresent());
        assertEquals(15, monthlyReport.get().getTotal());
        assertEquals(LocalDate.of(2019, 1, 1), monthlyReport.get().getDate());
    }

    @Test
    void getMonthlyReport_missing_record() {
        Optional<MonthlyRecordProjection> monthlyReport = meterReadingRepository
                .getMonthlyRecord(YearMonth.of(2020, 9));
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
        List<MonthlyRecordProjection> yearlyReport = meterReadingRepository.getYearlyRecords(Year.of(year));
        assertEquals(expectedTotal, yearlyReport.stream().mapToLong(MonthlyRecordProjection::getTotal).sum());
    }
}