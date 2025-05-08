package com.mikepn.euclsystem.repositories;

import com.mikepn.euclsystem.models.Meter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IMeterRepository extends JpaRepository<Meter , UUID> {

    Optional<Meter> findByMeterNumber(String meterNumber);
    List<Meter> findMeterByCustomer_Id(UUID id);
}
