package com.ghandreisv.meter.api;

import com.ghandreisv.meter.api.dto.MonthlyReportDto;
import com.ghandreisv.meter.api.dto.YearlyReportDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.time.Year;

@RestController
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/yearly")
    public YearlyReportDto getYearlyDetailedReport(@RequestParam("year") Year year,
                                                   @RequestParam(value = "detailed", required = false, defaultValue = "false") Boolean detailed) {
        return reportService.getYearlyReport(year, detailed);
    }

    @GetMapping("/monthly")
    public MonthlyReportDto getMonthlyReport(@RequestParam("year") Year year,
                                             @RequestParam("month") Month month) {
        return reportService.getMonthlyReport(year, month);
    }

}
