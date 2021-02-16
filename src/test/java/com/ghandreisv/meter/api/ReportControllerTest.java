package com.ghandreisv.meter.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ghandreisv.meter.api.dto.DetailedYearlyReportDto;
import com.ghandreisv.meter.api.dto.MonthlyRecordDto;
import com.ghandreisv.meter.api.dto.MonthlyReportDto;
import com.ghandreisv.meter.api.dto.YearlyReportDto;
import com.ghandreisv.meter.api.reports.DetailedYearlyReportCreator;
import com.ghandreisv.meter.api.reports.YearlyReportCreator;
import com.ghandreisv.meter.service.ReportService;
import com.ghandreisv.meter.service.report.MonthlyRecordProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Month;
import java.time.Year;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.ghandreisv.meter.service.report.MonthlyRecordProjectionFixture.withDateAndTotal;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
class ReportControllerTest {

    @MockBean(name = "yearlyReportCreator")
    private YearlyReportCreator yearlyReportCreator;
    @MockBean(name = "detailedYearlyReportCreator")
    private DetailedYearlyReportCreator detailedYearlyReportCreator;
    @MockBean
    private ReportService reportService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private final Year year = Year.of(2021);
    private final Integer month = Month.JANUARY.getValue();

    @Test
    void should_get_yearly_detailed_report() throws Exception {
        MonthlyRecordProjection monthlyRecordProjection = withDateAndTotal(year.getValue(), month, 11L);
        List<MonthlyRecordProjection> recordProjections = Collections.singletonList(monthlyRecordProjection);
        YearlyReportDto expected = new YearlyReportDto(year, monthlyRecordProjection.getValue());

        when(reportService.getYearlyReport(year))
                .thenReturn(recordProjections);
        when(yearlyReportCreator.createReport(year, recordProjections)).thenReturn(expected);

        mockMvc.perform(get("/reports/yearly?year="+year))
                .andExpect(status().isOk())
                .andExpect(content().json(asJson(expected)));
    }

    @Test
    void should_get_yearly_detailed_report_detailed() throws Exception {
        MonthlyRecordProjection monthlyRecordProjection = withDateAndTotal(year.getValue(), month, 11L);
        List<MonthlyRecordProjection> recordProjections = Collections.singletonList(monthlyRecordProjection);
        List<MonthlyRecordDto> monthlyRecordDtos = Collections.singletonList(new MonthlyRecordDto(Month.of(month), monthlyRecordProjection.getValue()));
        DetailedYearlyReportDto expected = new DetailedYearlyReportDto(year, monthlyRecordProjection.getValue(), monthlyRecordDtos);

        when(reportService.getYearlyReport(year))
                .thenReturn(recordProjections);
        when(detailedYearlyReportCreator.createReport(year, recordProjections)).thenReturn(expected);

        mockMvc.perform(get("/reports/yearly?year="+year+"&detailed=true"))
                .andExpect(status().isOk())
                .andExpect(content().json(asJson(expected)));
    }

    @Test
    void should_get_monthly_report() throws Exception {
        MonthlyRecordProjection monthlyRecordProjection = withDateAndTotal(year.getValue(), month, 11L);
        MonthlyReportDto expected = new MonthlyReportDto(year, Month.of(month), monthlyRecordProjection.getValue());

        when(reportService.getMonthlyReport(year, Month.of(month)))
                .thenReturn(Optional.of(monthlyRecordProjection));

        mockMvc.perform(get("/reports/monthly?year="+year + "&month=" + month))
                .andExpect(status().isOk())
                .andExpect(content().json(asJson(expected)));
    }

    private String asJson(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }
}