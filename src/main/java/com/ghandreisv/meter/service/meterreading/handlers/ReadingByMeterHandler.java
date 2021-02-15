package com.ghandreisv.meter.service.meterreading.handlers;

import com.ghandreisv.meter.api.exceptions.EntityNotFoundException;
import com.ghandreisv.meter.api.exceptions.MeterReadingAlreadyExistsException;
import com.ghandreisv.meter.service.meter.Meter;
import com.ghandreisv.meter.service.meter.MeterRepository;
import com.ghandreisv.meter.service.meterreading.MeterReadingRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Component("byMeterHandler")
public class ReadingByMeterHandler extends MeterReadingHandlerImpl {

    public ReadingByMeterHandler(MeterRepository meterRepository, MeterReadingRepository meterReadingRepository) {
        super(meterRepository, meterReadingRepository);
    }

    @Override
    protected BiConsumer<String, LocalDate> getReadingExistenceValidator() {
        return (meterId, date) -> {
            if (meterReadingRepository.existsByMeterIdAndDate(meterId, date)) {
                throw new MeterReadingAlreadyExistsException(meterId, Meter.ENTITY_TYPE, date);
            }
        };
    }

    @Override
    protected Function<String, Meter> getMeterFinder() {
        return meterId -> meterRepository.findById(meterId)
                .orElseThrow(() -> new EntityNotFoundException(meterId, Meter.ENTITY_TYPE));
    }
}
