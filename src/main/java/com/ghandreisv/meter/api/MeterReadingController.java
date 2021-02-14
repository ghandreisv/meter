package com.ghandreisv.meter.api;

import com.ghandreisv.meter.api.dto.MeterReadingDto;
import com.ghandreisv.meter.service.MeterReadingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/meter-readings")
public class MeterReadingController {

    private final MeterReadingService meterReadingService;

    public MeterReadingController(MeterReadingService meterReadingService) {
        this.meterReadingService = meterReadingService;
    }

    @PostMapping
    public String addMeterReading(@Valid @RequestBody MeterReadingDto meterReadingDto) {
        String result;
        if (meterReadingDto.getMeterId() != null) {
            result = meterReadingService.addMeterReadingByMeterId(
                    meterReadingDto.getMeterId(),
                    meterReadingDto.getDate(),
                    meterReadingDto.getValue());
        } else if (meterReadingDto.getAddressId() != null) {
            result = meterReadingService.addMeterReadingByAddressId(
                    meterReadingDto.getAddressId(),
                    meterReadingDto.getDate(),
                    meterReadingDto.getValue());
        } else if (meterReadingDto.getClientId() != null) {
            result = meterReadingService.addMeterReadingByClientId(
                    meterReadingDto.getClientId(),
                    meterReadingDto.getDate(),
                    meterReadingDto.getValue());
        } else {
            throw new IllegalArgumentException("No suitable meter reading id found, expected at least one meter, address or client: "
                    + meterReadingDto);
        }
        return result;
    }
}
