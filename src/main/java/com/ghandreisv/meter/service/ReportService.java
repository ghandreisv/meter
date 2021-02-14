package com.ghandreisv.meter.service;

import com.ghandreisv.meter.api.dto.MonthlyReportDto;
import com.ghandreisv.meter.api.dto.YearlyReportDto;
import com.ghandreisv.meter.api.reports.DetailedYearlyReportCreator;
import com.ghandreisv.meter.api.reports.YearlyReportCreator;
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
    private final YearlyReportCreator yearlyReportCreator;
    private final DetailedYearlyReportCreator detailedYearlyReportCreator;


    public ReportService(MeterReadingRepository meterReadingRepository,
                         YearlyReportCreator yearlyReportCreator,
                         DetailedYearlyReportCreator detailedYearlyReportCreator) {
        this.meterReadingRepository = meterReadingRepository;
        this.yearlyReportCreator = yearlyReportCreator;
        this.detailedYearlyReportCreator = detailedYearlyReportCreator;
    }

    public YearlyReportDto getYearlyReport(Year year, Boolean detailed) {
        List<MonthlyRecordProjection> records = meterReadingRepository.getYearlyRecords(year);
        return detailed
                ? detailedYearlyReportCreator.createReport(year, records)
                : yearlyReportCreator.createReport(year, records);
    }

    public MonthlyReportDto getMonthlyReport(Year year, Month month) {
        Optional<MonthlyRecordProjection> monthlyRecord = meterReadingRepository.getMonthlyRecord(YearMonth.of(year.getValue(), month));
        return new MonthlyReportDto(year, month, monthlyRecord.map(MonthlyRecordProjection::getValue).orElse(0L));
    }
}
