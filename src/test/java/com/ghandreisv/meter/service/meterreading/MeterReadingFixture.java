package com.ghandreisv.meter.service.meterreading;

import com.ghandreisv.meter.service.meter.MeterFixture;

import java.time.LocalDate;

public class MeterReadingFixture {

    public static MeterReading create() {
        return new MeterReading(
                MeterFixture.create(),
                LocalDate.of(2020, 2, 15),
                11L
        );
    }

}
