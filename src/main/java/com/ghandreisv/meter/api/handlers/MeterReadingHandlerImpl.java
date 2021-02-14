package com.ghandreisv.meter.api.handlers;

import com.ghandreisv.meter.api.dto.MeterReadingDto;
import com.ghandreisv.meter.model.Meter;
import com.ghandreisv.meter.model.MeterReading;
import com.ghandreisv.meter.repository.MeterReadingRepository;
import com.ghandreisv.meter.repository.MeterRepository;
import com.ghandreisv.meter.util.IdentityProvider;

import java.time.LocalDate;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class MeterReadingHandlerImpl implements MeterReadingHandler {

    private final IdentityProvider<String> identityProvider;
    protected final MeterRepository meterRepository;
    protected final MeterReadingRepository meterReadingRepository;

    public MeterReadingHandlerImpl(IdentityProvider<String> identityProvider,
                                   MeterRepository meterRepository,
                                   MeterReadingRepository meterReadingRepository) {
        this.identityProvider = identityProvider;
        this.meterRepository = meterRepository;
        this.meterReadingRepository = meterReadingRepository;
    }

    protected abstract Function<MeterReadingDto, String> getSourceEntityIdProvider();

    protected abstract BiConsumer<String, LocalDate> getReadingExistenceValidator();

    protected abstract Function<String, Meter> getMeterFinder();

    @Override
    public String handle(MeterReadingDto meterReadingDto) {
        String sourceEntityId = getSourceEntityId(meterReadingDto);
        getReadingExistenceValidator().accept(sourceEntityId, meterReadingDto.getYearMonth().atDay(1));
        Meter meter = getMeterFinder().apply(sourceEntityId);
        MeterReading meterReading = new MeterReading(
                identityProvider.createIdentity(),
                meter,
                meterReadingDto.getYearMonth().atDay(1),
                meterReadingDto.getValue()
        );
        return meterReadingRepository.save(meterReading).getId();
    }

    private String getSourceEntityId(MeterReadingDto meterReadingDto) {
        return Optional.ofNullable(meterReadingDto)
                .map(getSourceEntityIdProvider())
                .orElseThrow(() -> new IllegalArgumentException("Source entity identifier must not be null"));
    }

}
