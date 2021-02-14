package com.ghandreisv.meter.api;

import com.ghandreisv.meter.api.dto.MeterReadingDto;
import com.ghandreisv.meter.service.MeterReadingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/meter-readings")
public class MeterReadingController {

    private final MeterReadingService meterReadingService;

    public MeterReadingController(MeterReadingService meterReadingService) {
        this.meterReadingService = meterReadingService;
    }

    @PostMapping
    public String addMeterReading(@Valid @RequestBody MeterReadingDto meterReadingDto) {
        return Optional.ofNullable(meterReadingDto)
                .map(readingDto -> {
                    String result = null;
                    if (readingDto.getMeterId() != null) {
                        result = meterReadingService.addMeterReadingByMeterId(
                                readingDto.getMeterId(),
                                readingDto.getDate(),
                                meterReadingDto.getValue());
                    } else if (readingDto.getAddressId() != null) {
                        result = meterReadingService.addMeterReadingByAddressId(
                                readingDto.getAddressId(),
                                readingDto.getDate(),
                                meterReadingDto.getValue());
                    } else if (readingDto.getClientId() != null) {
                        result = meterReadingService.addMeterReadingByClientId(
                                readingDto.getClientId(),
                                readingDto.getDate(),
                                meterReadingDto.getValue());
                    }
                    return result;
                }).orElseThrow(() -> new IllegalArgumentException("No suitable meter reading id found, expected at least one meter, address or client: "
                        + meterReadingDto));
    }
}
