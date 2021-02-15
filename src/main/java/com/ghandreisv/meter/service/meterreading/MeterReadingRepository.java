package com.ghandreisv.meter.service.meterreading;

import com.ghandreisv.meter.service.report.MonthlyRecordProjection;
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

    @Query("SELECT CASE WHEN COUNT(mr) > 0 THEN true ELSE false END FROM MeterReading mr " +
            "WHERE mr.meter.id = :meterId AND month(mr.date)=month(:date) AND year(mr.date)=year(:date)")
    boolean existsByMeterIdAndDate(String meterId, LocalDate date);

    @Query("SELECT CASE WHEN COUNT(mr) > 0 THEN true ELSE false END FROM MeterReading mr, Meter m, Address a, Client c " +
            "WHERE mr.meter=m AND month(mr.date)=month(:date) AND year(mr.date)=year(:date) AND m.address=a AND c.address=a AND c.id=:clientId")
    boolean existsByClientAndDate(String clientId, LocalDate date);

    @Query("SELECT CASE WHEN COUNT(mr) > 0 THEN true ELSE false END FROM MeterReading mr, Meter m, Address a " +
            "WHERE mr.meter=m AND month(mr.date)=month(:date) AND year(mr.date)=year(:date) AND m.address=a AND a.id=:addressId")
    boolean existsByAddressAndDate(String addressId, LocalDate date);

    @Query("SELECT year(mr.date) as year, (month(mr.date)-1) as month, SUM(mr.value) as value" +
            " FROM MeterReading mr WHERE mr.date between :#{#year.atDay(1)} and :#{#year.atDay(#year.length())} GROUP BY year(mr.date), (month(mr.date)-1)")
    List<MonthlyRecordProjection> getYearlyRecords(@Param("year") Year year);

    @Query("SELECT year(mr.date) as year, (month(mr.date)-1) as month, SUM(mr.value) as value" +
            " FROM MeterReading mr WHERE mr.date between :#{#yearMonth.atDay(1)} and :#{#yearMonth.atDay(#yearMonth.lengthOfMonth())} group by year(mr.date), (month(mr.date)-1)")
    Optional<MonthlyRecordProjection> getMonthlyRecord(@Param("yearMonth") YearMonth yearMonth);
}
