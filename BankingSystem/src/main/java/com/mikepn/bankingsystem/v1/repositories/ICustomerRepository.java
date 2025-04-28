package com.mikepn.bankingsystem.v1.repositories;


import com.mikepn.bankingsystem.v1.models.Customer;
import com.mikepn.bankingsystem.v1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICustomerRepository extends JpaRepository<Customer, UUID> {

    boolean existsByProfile_Email(String email);

    Optional<Customer> findByProfile(User profile);
}
