package com.ghandreisv.meter.service;

import com.ghandreisv.meter.model.MonthlyRecordProjection;
import com.ghandreisv.meter.service.meterreading.MeterReadingRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final MeterReadingRepository meterReadingRepository;

    public ReportService(MeterReadingRepository meterReadingRepository) {
        this.meterReadingRepository = meterReadingRepository;
    }

    public List<MonthlyRecordProjection> getYearlyReport(Year year) {
        return meterReadingRepository.getYearlyRecords(year);
    }

    public Optional<MonthlyRecordProjection> getMonthlyReport(Year year, Month month) {
        return meterReadingRepository.getMonthlyRecord(YearMonth.of(year.getValue(), month));
    }
}
