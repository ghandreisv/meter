package com.ghandreisv.meter.service.meter;

import com.ghandreisv.meter.service.address.AddressFixture;

import java.util.UUID;

public class MeterFixture {

    public static Meter create() {
        return new Meter(
                UUID.randomUUID().toString(),
                AddressFixture.create()
        );
    }
}
