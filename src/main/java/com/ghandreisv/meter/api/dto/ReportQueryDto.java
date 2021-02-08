package com.ghandreisv.meter.api.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Month;
import java.time.Year;

@Data
public class ReportQueryDto {
    @NotNull
    Year year;
    @Min(1) @Max(12)
    Integer month = null;
    Boolean detailed = false;
}
