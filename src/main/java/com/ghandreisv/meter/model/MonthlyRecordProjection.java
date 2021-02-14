package com.ghandreisv.meter.model;

import java.time.Month;

public interface MonthlyRecordProjection {
     Integer getYear();
     Month getMonth();
     Long getValue();
}
