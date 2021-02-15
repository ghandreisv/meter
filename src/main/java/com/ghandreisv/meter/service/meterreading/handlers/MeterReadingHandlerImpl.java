package com.ghandreisv.meter.service.meterreading.handlers;

import com.ghandreisv.meter.service.meter.Meter;
import com.ghandreisv.meter.service.meter.MeterRepository;
import com.ghandreisv.meter.service.meterreading.MeterReading;
import com.ghandreisv.meter.service.meterreading.MeterReadingRepository;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Validated
public abstract class MeterReadingHandlerImpl implements MeterReadingHandler {

    protected final MeterRepository meterRepository;
    protected final MeterReadingRepository meterReadingRepository;

    public MeterReadingHandlerImpl(MeterRepository meterRepository,
                                   MeterReadingRepository meterReadingRepository) {
        this.meterRepository = meterRepository;
        this.meterReadingRepository = meterReadingRepository;
    }

    protected abstract BiConsumer<String, LocalDate> getReadingExistenceValidator();

    protected abstract Function<String, Meter> getMeterFinder();

    @Override
    public String handle(String id, LocalDate date, Long value) {
        getReadingExistenceValidator().accept(id, date);
        Meter meter = getMeterFinder().apply(id);
        MeterReading meterReading = new MeterReading(meter, date, value);
        return meterReadingRepository.save(meterReading).getId();
    }

}
