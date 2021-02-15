package com.ghandreisv.meter.service.meterreading.handlers;

import com.ghandreisv.meter.api.exceptions.EntityNotFoundException;
import com.ghandreisv.meter.api.exceptions.MeterReadingAlreadyExistsException;
import com.ghandreisv.meter.service.meter.MeterRepository;
import com.ghandreisv.meter.service.meterreading.MeterReading;
import com.ghandreisv.meter.service.meterreading.MeterReadingFixture;
import com.ghandreisv.meter.service.meterreading.MeterReadingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReadingByMeterHandlerTest {

    @Mock
    private MeterReadingRepository meterReadingRepository;
    @Mock
    private MeterRepository meterRepository;

    @InjectMocks
    private ReadingByMeterHandler handler;

    private final String addressId = UUID.randomUUID().toString();
    private final MeterReading meterReading = MeterReadingFixture.create();

    @Test
    void should_handle() {
        MeterReading savedMeterReading = new MeterReading(meterReading.getMeter(), meterReading.getDate(), meterReading.getValue());
        ReflectionTestUtils.setField(savedMeterReading, "id", UUID.randomUUID().toString());

        when(meterReadingRepository.existsByMeterIdAndDate(any(), any())).thenReturn(false);
        when(meterRepository.findById(any())).thenReturn(Optional.of(meterReading.getMeter()));
        when(meterReadingRepository.save(any())).thenReturn(savedMeterReading);

        String meterReadingId = handler.handle(addressId, meterReading.getDate(), meterReading.getValue());

        assertEquals(savedMeterReading.getId(), meterReadingId);

        verify(meterReadingRepository).existsByMeterIdAndDate(addressId, meterReading.getDate());
        verify(meterRepository).findById(addressId);
        verify(meterReadingRepository).save(meterReading);
    }

    @Test
    void should_not_handle_if_already_exists() {
        when(meterReadingRepository.existsByMeterIdAndDate(any(), any())).thenReturn(true);
        assertThrows(MeterReadingAlreadyExistsException.class, () -> handler.handle(addressId, meterReading.getDate(), meterReading.getValue()));
        verify(meterReadingRepository).existsByMeterIdAndDate(addressId, meterReading.getDate());
    }

    @Test
    void should_not_handle_if_meter_not_found() {
        when(meterReadingRepository.existsByMeterIdAndDate(any(), any())).thenReturn(false);
        when(meterRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> handler.handle(addressId, meterReading.getDate(), meterReading.getValue()));

        verify(meterReadingRepository).existsByMeterIdAndDate(addressId, meterReading.getDate());
        verify(meterRepository).findById(addressId);
    }
}