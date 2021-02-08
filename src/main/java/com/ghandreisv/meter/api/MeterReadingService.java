package com.ghandreisv.meter.api;

import com.ghandreisv.meter.api.dto.MeterReadingDto;
import com.ghandreisv.meter.api.handlers.MeterReadingHandlerSupplier;
import org.springframework.stereotype.Service;

@Service
public class MeterReadingService {

    private final MeterReadingHandlerSupplier readingHandlerSupplier;

    public MeterReadingService(MeterReadingHandlerSupplier readingHandlerSupplier) {
        this.readingHandlerSupplier = readingHandlerSupplier;
    }

    public String addMeterReading(MeterReadingDto meterReadingDto) {
        return readingHandlerSupplier.apply(meterReadingDto).handle(meterReadingDto);
    }

}
