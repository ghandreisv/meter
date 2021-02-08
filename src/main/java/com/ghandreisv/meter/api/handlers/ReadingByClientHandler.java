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

@Component("byClientHandler")
public class ReadingByClientHandler extends MeterReadingHandlerImpl {

    public ReadingByClientHandler(IdentityProvider<String> identityProvider,
                                  MeterRepository meterRepository,
                                  MeterReadingRepository meterReadingRepository) {
        super(identityProvider, meterRepository, meterReadingRepository);
    }

    @Override
    protected BiConsumer<String, LocalDate> getReadingExistenceValidator() {
        return (clientId, date) ->
                meterReadingRepository.findByClientAndDate(clientId, date)
                        .ifPresent(id -> {
                            throw new MeterReadingAlreadyExistsException(clientId, Client.ENTITY_TYPE, date);
                        });
    }

    @Override
    protected Function<String, Meter> getMeterFinder() {
        return clientId -> meterRepository.findByClientId(clientId)
                .orElseThrow(() -> new EntityNotFoundException(clientId, Client.ENTITY_TYPE, Meter.ENTITY_TYPE));
    }

    @Override
    protected Function<MeterReadingDto, String> getSourceEntityIdProvider() {
        return MeterReadingDto::getClientId;
    }
}
