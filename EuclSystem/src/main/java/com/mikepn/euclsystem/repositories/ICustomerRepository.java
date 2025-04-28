package com.mikepn.euclsystem.repositories;

import com.mikepn.euclsystem.models.Customer;
import com.mikepn.euclsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICustomerRepository extends JpaRepository<Customer, UUID> {


    boolean existsByProfile_Email(String email);

    Optional<Customer> findByProfile(User profile);

    Optional<Customer> findByMeters_MeterNumber(String metersMeterNumber);

}
