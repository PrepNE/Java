package com.mikepn.vehiclemanagementsystem.repositories;

import com.mikepn.vehiclemanagementsystem.models.Owner;
import com.mikepn.vehiclemanagementsystem.models.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IVehicleRepository extends JpaRepository <Vehicle , UUID>{

    Page<Vehicle> findByOwner(Owner owner, Pageable pageable);
    boolean existsByChassisNumber(String chassisNumber);

    Optional<Vehicle> findVehicleByOwner_Profile_NationalId(String nationalId);
    Optional<Vehicle> findVehicleByCurrentPlate_PlateNumber(String plate);
    Optional<Vehicle> findVehicleByChassisNumber(String chassisNumber);
}
