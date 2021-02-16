package com.ghandreisv.meter.service.report;

public class MonthlyRecordProjectionFixture {

    public static MonthlyRecordProjection withDateAndTotal(Integer year, Integer month, Long total) {
        return new MonthlyRecordProjection() {
            @Override
            public Integer getYear() {
                return year;
            }

            @Override
            public Integer getMonth() {
                return month;
            }

            @Override
            public Long getValue() {
                return total;
            }
        };
    }

}
