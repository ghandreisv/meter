package com.ghandreisv.meter.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghandreisv.meter.api.dto.MeterReadingsReportDto;
import com.ghandreisv.meter.api.dto.ReportQueryDto;
import com.ghandreisv.meter.api.propertyeditor.ReportQueryPropertyEditor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/report")
@Validated
public class ReportController {

    private final ObjectMapper objectMapper;
    private final ReportService reportService;

    public ReportController(ObjectMapper objectMapper, ReportService reportService) {
        this.objectMapper = objectMapper;
        this.reportService = reportService;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(ReportQueryDto.class, new ReportQueryPropertyEditor(objectMapper));
    }

    @GetMapping
    public MeterReadingsReportDto getMeterReadingReport(@Valid @NotNull @RequestParam("json") ReportQueryDto reportQueryDto) {
        return reportService.getReport(reportQueryDto);
    }

}
