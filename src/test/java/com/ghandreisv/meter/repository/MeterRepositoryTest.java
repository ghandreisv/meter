package com.ghandreisv.meter.repository;

import com.ghandreisv.meter.model.Meter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class MeterRepositoryTest {
    @Autowired
    private MeterRepository meterRepository;

    @Test
    void findByClientId() {
        String expectedMeterId = "4758677d-bb43-4f21-9bdf-98349b30003e";
        Optional<Meter> meter = meterRepository.findByClientId("5d4a1603-5456-4340-9e1f-fb6861573570");
        assertTrue(meter.isPresent());
        assertEquals(expectedMeterId, meter.get().getId());
    }

    @Test
    void findByAddressId() {
        String expectedMeterId = "4758677d-bb43-4f21-9bdf-98349b30003e";
        Optional<Meter> meter = meterRepository.findByAddressId("0ad46436-532d-4bfb-92e6-654e036b6cda");
        assertTrue(meter.isPresent());
        assertEquals(expectedMeterId, meter.get().getId());
    }
}
