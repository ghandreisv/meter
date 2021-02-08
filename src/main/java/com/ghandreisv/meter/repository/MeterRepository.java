package com.ghandreisv.meter.repository;

import com.ghandreisv.meter.model.Meter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MeterRepository extends CrudRepository<Meter, String> {

    @Query("SELECT m FROM Meter m, Address a, Client c WHERE m.address=a AND c.address=a AND c.id=:clientId")
    Optional<Meter> findByClientId(String clientId);

    Optional<Meter> findByAddressId(String addressId);

}
