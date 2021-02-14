package com.ghandreisv.meter.service;

import com.ghandreisv.meter.service.meterreading.handlers.MeterReadingHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MeterReadingService {
    private final MeterReadingHandler byClientHandler;
    private final MeterReadingHandler byAddressHandler;
    private final MeterReadingHandler byMeterHandler;

    public MeterReadingService(@Qualifier("byClientHandler") MeterReadingHandler byClientHandler,
                               @Qualifier("byAddressHandler") MeterReadingHandler byAddressHandler,
                               @Qualifier("byMeterHandler") MeterReadingHandler byMeterHandler) {
        this.byClientHandler = byClientHandler;
        this.byAddressHandler = byAddressHandler;
        this.byMeterHandler = byMeterHandler;
    }

    public String addMeterReadingByMeterId(String meterId, LocalDate date, Long value) {
        return byMeterHandler.handle(meterId, date, value);
    }

    public String addMeterReadingByAddressId(String addressId, LocalDate date, Long value) {
        return byAddressHandler.handle(addressId, date, value);
    }

    public String addMeterReadingByClientId(String clientId, LocalDate date, Long value) {
        return byClientHandler.handle(clientId, date, value);
    }
}
