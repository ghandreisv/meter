package com.ghandreisv.meter.api.handlers;

import com.ghandreisv.meter.api.dto.MeterReadingDto;
import com.ghandreisv.meter.api.exceptions.EntityNotFoundException;
import com.ghandreisv.meter.api.exceptions.MeterReadingAlreadyExistsException;
import com.ghandreisv.meter.model.Client;
import com.ghandreisv.meter.model.Meter;
import com.ghandreisv.meter.repository.MeterReadingRepository;
import com.ghandreisv.meter.repository.MeterRepository;
import com.ghandreisv.meter.util.IdentityProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Component("byMeterHandler")
public class ReadingByMeterHandler extends MeterReadingHandlerImpl {

    public ReadingByMeterHandler(IdentityProvider<String> identityProvider,
                                 MeterRepository meterRepository,
                                 MeterReadingRepository meterReadingRepository) {
        super(identityProvider, meterRepository, meterReadingRepository);
    }

    @Override
    protected Function<MeterReadingDto, String> getSourceEntityIdProvider() {
        return MeterReadingDto::getMeterId;
    }

    @Override
    protected BiConsumer<String, LocalDate> getReadingExistenceValidator() {
        return (meterId, date) -> meterReadingRepository.findByMeterIdAndDate(meterId, date)
                .ifPresent(id -> {
                    throw new MeterReadingAlreadyExistsException(meterId, Client.ENTITY_TYPE, date);
                });
    }

    @Override
    protected Function<String, Meter> getMeterFinder() {
        return meterId -> meterRepository.findById(meterId)
                .orElseThrow(() -> new EntityNotFoundException(meterId, Meter.ENTITY_TYPE));
    }
}
