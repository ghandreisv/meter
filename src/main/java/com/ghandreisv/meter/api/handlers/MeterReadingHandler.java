package com.ghandreisv.meter.api.handlers;

import com.ghandreisv.meter.api.dto.MeterReadingDto;

public interface MeterReadingHandler {

    String handle(MeterReadingDto meterReadingDto);

}
