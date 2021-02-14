package com.ghandreisv.meter.repository;

import com.ghandreisv.meter.service.meterreading.MeterReadingRepository;
import com.ghandreisv.meter.service.report.MonthlyRecordProjection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.Month;
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
                .existsByClientAndDate("5d4a1603-5456-4340-9e1f-fb6861573570", LocalDate.of(2019, 1, 15));
        assertTrue(exists);
    }

    @Test
    void existsByClientAndDate_not_found() {
        boolean exists = meterReadingRepository
                .existsByClientAndDate("not existing id", LocalDate.of(2019, 1, 12));
        assertFalse(exists);
    }

    @Test
    void existsByAddressAndDate() {
        boolean exists = meterReadingRepository
                .existsByAddressAndDate("0ad46436-532d-4bfb-92e6-654e036b6cda", LocalDate.of(2019, 1, 13));
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
                .existsByMeterIdAndDate("4758677d-bb43-4f21-9bdf-98349b30003e", LocalDate.of(2019, 1, 22));
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
        YearMonth yearMonth = YearMonth.of(2019, Month.JANUARY);
        Optional<MonthlyRecordProjection> monthlyReport = meterReadingRepository.getMonthlyRecord(yearMonth);
        assertTrue(monthlyReport.isPresent());
        assertEquals(yearMonth.getYear(), monthlyReport.get().getYear());
        assertEquals(yearMonth.getMonth(), monthlyReport.get().getMonth());
        assertEquals(15, monthlyReport.get().getValue());
    }

    @Test
    void getMonthlyReport_missing_record() {
        Optional<MonthlyRecordProjection> monthlyReport = meterReadingRepository
                .getMonthlyRecord(YearMonth.of(2000, 9));
        assertFalse(monthlyReport.isPresent());
    }

    @ParameterizedTest
    @CsvSource({
            "2019, 5, 105",
            "2020, 5, 159",
            "1997, 0, 0"
    })
    void testYearlyReport(int year, int size, long total) {
        List<MonthlyRecordProjection> yearlyReport = meterReadingRepository.getYearlyRecords(Year.of(year));
        assertEquals(size, yearlyReport.size());
        assertEquals(total, yearlyReport.stream().mapToLong(MonthlyRecordProjection::getValue).sum());
    }
}