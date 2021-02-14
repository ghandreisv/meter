package com.ghandreisv.meter.api;

import com.ghandreisv.meter.api.dto.MeterReadingDto;
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
        return meterReadingService.addMeterReading(meterReadingDto);
    }
}
