package com.ghandreisv.meter.api.handlers;

import com.ghandreisv.meter.api.dto.MeterReadingDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class MeterReadingHandlerSupplier implements Function<MeterReadingDto, MeterReadingHandler> {

    private final MeterReadingHandler byClientHandler;
    private final MeterReadingHandler byAddressHandler;
    private final MeterReadingHandler byMeterHandler;

    public MeterReadingHandlerSupplier(@Qualifier("byClientHandler") MeterReadingHandler byClientHandler,
                                       @Qualifier("byClientHandler") MeterReadingHandler byAddressHandler,
                                       @Qualifier("byClientHandler") MeterReadingHandler byMeterHandler) {
        this.byClientHandler = byClientHandler;
        this.byAddressHandler = byAddressHandler;
        this.byMeterHandler = byMeterHandler;
    }

    @Override
    public MeterReadingHandler apply(MeterReadingDto meterReadingDto) {
        return Optional.ofNullable(meterReadingDto)
                .map(readingDto -> {
                    MeterReadingHandler handler = null;
                    if (readingDto.getMeterId() != null) {
                        handler = byMeterHandler;
                    } else if (readingDto.getAddressId() != null) {
                        handler = byAddressHandler;
                    } else if (readingDto.getClientId() != null) {
                        handler = byClientHandler;
                    }
                    return handler;
                }).orElseThrow(() -> new IllegalArgumentException("No suitable handler found for given meter reading: " + meterReadingDto));
    }
}
