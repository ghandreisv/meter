package com.ghandreisv.meter.api.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class MeterReadingDto {
    //TODO: validate at least one field is not empty
    String meterId;
    String clientId;
    String addressId;
    @NotNull
    LocalDate date;
    @NotNull
    Long value;
}
