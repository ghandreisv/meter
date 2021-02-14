package com.ghandreisv.meter.api.dto;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Month;

@Component
public class MonthConverter implements Converter<String, Month> {

    @Override
    public Month convert(String source) {
        boolean isNumber = source.chars().allMatch(Character::isDigit);
        return isNumber ? Month.of(Integer.parseInt(source)) : Month.valueOf(source.toUpperCase());
    }
}
