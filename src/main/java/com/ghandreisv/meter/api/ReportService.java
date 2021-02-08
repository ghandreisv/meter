package com.ghandreisv.meter.api;

import com.ghandreisv.meter.api.dto.MeterReadingsReportDto;
import com.ghandreisv.meter.api.dto.ReportQueryDto;
import com.ghandreisv.meter.model.projection.MonthlyReportProjection;
import com.ghandreisv.meter.repository.MeterReadingRepository;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final MeterReadingRepository meterReadingRepository;

    public ReportService(MeterReadingRepository meterReadingRepository) {
        this.meterReadingRepository = meterReadingRepository;
    }

    public MeterReadingsReportDto getReport(ReportQueryDto reportQueryDto) {
        List<MonthlyReportProjection> report = new ArrayList<>();
        if (reportQueryDto.getMonth() != null) {
            meterReadingRepository.getMonthlyReport(
                    YearMonth.of(
                            reportQueryDto.getYear().getValue(),
                            reportQueryDto.getMonth())
            ).ifPresent(report::add);
        } else {
            report.addAll(meterReadingRepository.getYearlyReport(reportQueryDto.getYear()));
        }
        return createReport(report, reportQueryDto);
    }

    private MeterReadingsReportDto createReport(List<MonthlyReportProjection> reportRecords, ReportQueryDto reportQueryDto) {
        Integer year = reportQueryDto.getYear().getValue();
        Long total = reportQueryDto.getMonth() == null ? reportRecords.stream().mapToLong(MonthlyReportProjection::getTotal).sum() : null;
        Map<Month, Long> monthly = Boolean.TRUE.equals(reportQueryDto.getDetailed())
                ? reportRecords.stream().collect(Collectors.toMap(t -> t.getDate().getMonth(), MonthlyReportProjection::getTotal))
                : null;
        return new MeterReadingsReportDto(year, total, monthly);
    }
}
