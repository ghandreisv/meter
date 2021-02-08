package com.ghandreisv.meter.repository;

import com.ghandreisv.meter.model.MeterReading;
import com.ghandreisv.meter.model.projection.MeterReadingId;
import com.ghandreisv.meter.model.projection.MonthlyReportProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface MeterReadingRepository extends CrudRepository<MeterReading, String> {
    @Query("SELECT mr FROM MeterReading mr, Meter m, Address a, Client c WHERE mr.meter=m AND mr.date=:date AND m.address=a AND c.address=a AND c.id=:clientId")
    Optional<MeterReadingId> findByClientAndDate(String clientId, LocalDate date);

    @Query("SELECT mr FROM MeterReading mr, Meter m, Address a WHERE mr.meter=m AND mr.date=:date AND m.address=a AND a.id=:addressId ")
    Optional<MeterReadingId> findByAddressAndDate(String addressId, LocalDate date);

    Optional<MeterReadingId> findByMeterIdAndDate(String meterId, LocalDate date);

    @Query("SELECT mr.date as date, SUM(mr.value) as total" +
            " FROM MeterReading mr WHERE mr.date between :#{#year.atDay(1)} and :#{#year.atDay(#year.length())} GROUP BY mr.date")
    List<MonthlyReportProjection> getYearlyReport(@Param("year") Year year);

    @Query("SELECT mr.date as date, SUM(mr.value) as total" +
            " FROM MeterReading mr WHERE mr.date=:#{#yearMonth.atDay(1)} group by mr.date")
    Optional<MonthlyReportProjection> getMonthlyReport(@Param("yearMonth") YearMonth yearMonth);
}
