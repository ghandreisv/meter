package com.ghandreisv.meter.api.dto;

import com.ghandreisv.meter.api.validation.LeastOneNotNullField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.YearMonth;

@Data
@LeastOneNotNullField(fields = {"meterId", "clientId", "addressId"})
public class MeterReadingDto {
    String meterId;
    String clientId;
    String addressId;
    @NotNull(message = "'date' {javax.validation.constraints.NotNull.message}")
    YearMonth yearMonth;
    @NotNull(message = "'value' {javax.validation.constraints.NotNull.message}")
    Long value;
}
