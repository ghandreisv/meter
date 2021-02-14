package com.ghandreisv.meter.service.report;

import java.time.Month;

public class MonthlyRecordProjectionFixture {

    public static MonthlyRecordProjection withDateAndTotal(Integer year, Month month, Long total) {
        return new MonthlyRecordProjection() {
            @Override
            public Integer getYear() {
                return year;
            }

            @Override
            public Month getMonth() {
                return month;
            }

            @Override
            public Long getValue() {
                return total;
            }
        };
    }

}
