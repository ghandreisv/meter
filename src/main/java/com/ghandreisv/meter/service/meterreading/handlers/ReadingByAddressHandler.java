package com.ghandreisv.meter.service.meterreading.handlers;

import com.ghandreisv.meter.api.exceptions.EntityNotFoundException;
import com.ghandreisv.meter.api.exceptions.MeterReadingAlreadyExistsException;
import com.ghandreisv.meter.service.address.Address;
import com.ghandreisv.meter.service.meter.Meter;
import com.ghandreisv.meter.service.meter.MeterRepository;
import com.ghandreisv.meter.service.meterreading.MeterReadingRepository;
import com.ghandreisv.meter.util.IdentityProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Component("byAddressHandler")
public class ReadingByAddressHandler extends MeterReadingHandlerImpl {

    public ReadingByAddressHandler(IdentityProvider<String> identityProvider,
                                   MeterRepository meterRepository,
                                   MeterReadingRepository meterReadingRepository) {
        super(identityProvider, meterRepository, meterReadingRepository);
    }

    @Override
    protected BiConsumer<String, LocalDate> getReadingExistenceValidator() {
        return (addressId, date) -> {
            if (meterReadingRepository.existsByAddressAndDate(addressId, date)) {
                throw new MeterReadingAlreadyExistsException(addressId, Address.ENTITY_TYPE, date);
            }
        };
    }

    @Override
    protected Function<String, Meter> getMeterFinder() {
        return addressId -> meterRepository.findByAddressId(addressId)
                .orElseThrow(() -> new EntityNotFoundException(addressId, Address.ENTITY_TYPE, Meter.ENTITY_TYPE));
    }

}
