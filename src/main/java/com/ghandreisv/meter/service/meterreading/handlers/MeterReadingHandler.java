package com.ghandreisv.meter.service.meterreading.handlers;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public interface MeterReadingHandler {

    String handle(@NotBlank String id, @NotNull LocalDate date, @NotNull Long value);

}
