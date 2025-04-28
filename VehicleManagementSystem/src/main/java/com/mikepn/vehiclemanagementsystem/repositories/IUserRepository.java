package com.mikepn.vehiclemanagementsystem.repositories;

import com.mikepn.vehiclemanagementsystem.models.Role;
import com.mikepn.vehiclemanagementsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User , UUID> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findByRoles(Role role);

    Optional<User> findByVerificationCode(String verificationCode);
}
