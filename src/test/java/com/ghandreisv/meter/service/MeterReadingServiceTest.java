package com.ghandreisv.meter.service;

import com.ghandreisv.meter.service.meterreading.handlers.MeterReadingHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MeterReadingServiceTest {

    @Mock
    MeterReadingHandler byClientHandler;
    @Mock
    MeterReadingHandler byAddressHandler;
    @Mock
    MeterReadingHandler byMeterHandler;

    private MeterReadingService service;

    private final String id = "id1";
    private final LocalDate date = LocalDate.of(2021, 2, 16);
    private final Long value = 10L;
    private final String expected = "newId";

    @BeforeEach
    public void setUp() {
        service = new MeterReadingService(byClientHandler, byAddressHandler, byMeterHandler);
    }

    @Test
    void addMeterReadingByMeterId() {
        when(byMeterHandler.handle(id, date, value)).thenReturn(expected);

        String actual = service.addMeterReadingByMeterId(id, date, value);

        assertEquals(expected, actual);
        verify(byMeterHandler).handle(id, date, value);
    }

    @Test
    void addMeterReadingByAddressId() {
        when(byAddressHandler.handle(id, date, value)).thenReturn(expected);

        String actual = service.addMeterReadingByAddressId(id, date, value);

        assertEquals(expected, actual);
        verify(byAddressHandler).handle(id, date, value);
    }

    @Test
    void addMeterReadingByClientId() {
        when(byClientHandler.handle(id, date, value)).thenReturn(expected);

        String actual = service.addMeterReadingByClientId(id, date, value);

        assertEquals(expected, actual);
        verify(byClientHandler).handle(id, date, value);
    }
}