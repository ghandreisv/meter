package com.ghandreisv.meter.service.address;

import java.util.UUID;

public class AddressFixture {

    public static Address create() {
        return new Address(
                UUID.randomUUID().toString(),
                "street",
                "houseNumber",
                "city",
                "country"
        );
    }
}
