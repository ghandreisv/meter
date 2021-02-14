package com.ghandreisv.meter.api;

import com.ghandreisv.meter.api.dto.MonthlyReportDto;
import com.ghandreisv.meter.api.dto.YearlyReportDto;
import com.ghandreisv.meter.api.reports.DetailedYearlyReportCreator;
import com.ghandreisv.meter.api.reports.YearlyReportCreator;
import com.ghandreisv.meter.model.MonthlyRecordProjection;
import com.ghandreisv.meter.service.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.time.Year;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final YearlyReportCreator yearlyReportCreator;
    private final DetailedYearlyReportCreator detailedYearlyReportCreator;
    private final ReportService reportService;

    public ReportController(YearlyReportCreator yearlyReportCreator, DetailedYearlyReportCreator detailedYearlyReportCreator, ReportService reportService) {
        this.yearlyReportCreator = yearlyReportCreator;
        this.detailedYearlyReportCreator = detailedYearlyReportCreator;
        this.reportService = reportService;
    }

    @GetMapping("/yearly")
    public YearlyReportDto getYearlyDetailedReport(@RequestParam("year") Year year,
                                                   @RequestParam(value = "detailed", required = false, defaultValue = "false") Boolean detailed) {
        return detailed
                ? detailedYearlyReportCreator.createReport(year, reportService.getYearlyReport(year))
                : yearlyReportCreator.createReport(year, reportService.getYearlyReport(year));
    }

    @GetMapping("/monthly")
    public MonthlyReportDto getMonthlyReport(@RequestParam("year") Year year,
                                             @RequestParam("month") Month month) {
        return new MonthlyReportDto(year, month,
                reportService.getMonthlyReport(year, month)
                        .map(MonthlyRecordProjection::getValue)
                        .orElse(0L));
    }

}
