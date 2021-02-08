package com.ghandreisv.meter.api.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.YearMonth;

@Data
public class MeterReadingDto {
    //TODO: validate at least one field is not empty
    String meterId;
    String clientId;
    String addressId;
    @NotNull
    YearMonth date;
    @NotNull
    Long value;
}
