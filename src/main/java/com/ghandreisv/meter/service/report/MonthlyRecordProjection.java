package com.ghandreisv.meter.service.report;

import java.time.Month;

public interface MonthlyRecordProjection {
     Integer getYear();
     Month getMonth();
     Long getValue();
}
