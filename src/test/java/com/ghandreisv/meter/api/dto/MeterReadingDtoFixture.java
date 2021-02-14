package com.ghandreisv.meter.api.dto;

import java.time.LocalDate;
import java.util.UUID;

public class MeterReadingDtoFixture {

    public static MeterReadingDto withMeterId() {
        MeterReadingDto meterReadingDto = noIds();
        meterReadingDto.setMeterId(UUID.randomUUID().toString());
        return meterReadingDto;
    }

    public static MeterReadingDto withAddressId() {
        MeterReadingDto meterReadingDto = noIds();
        meterReadingDto.setAddressId(UUID.randomUUID().toString());
        return meterReadingDto;
    }

    public static MeterReadingDto withClientId() {
        MeterReadingDto meterReadingDto = noIds();
        meterReadingDto.setClientId(UUID.randomUUID().toString());
        return meterReadingDto;
    }

    public static MeterReadingDto noIds() {
        MeterReadingDto meterReadingDto = new MeterReadingDto();
        meterReadingDto.setDate(LocalDate.of(2021, 2, 15));
        meterReadingDto.setValue(11L);
        return meterReadingDto;
    }
}
