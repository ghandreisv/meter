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
    @NotNull
    YearMonth date;
    @NotNull
    Long value;
}
