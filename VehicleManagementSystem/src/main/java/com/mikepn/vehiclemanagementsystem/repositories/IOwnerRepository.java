package com.mikepn.vehiclemanagementsystem.repositories;

import com.mikepn.vehiclemanagementsystem.models.Owner;
import com.mikepn.vehiclemanagementsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IOwnerRepository extends JpaRepository <Owner, UUID>{

    boolean existsByProfile_Email(String email);
    Optional<Owner> findByProfile(User profile);
    Optional<Owner> findOwnerByProfile_NationalId(String nationalId);
    Optional<Owner> findOwnerByProfile_PhoneNumber(String phoneNumber);
    Optional<Owner> findOwnerByProfile_Email(String email);
}
