package com.mikepn.bankingsystem.v1.repositories;

import com.mikepn.bankingsystem.v1.models.Role;
import com.mikepn.bankingsystem.v1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {
     Optional<User> findUserByEmail(String email);

     Optional<User> findByRoles(Role role);

     Optional<User> findByVerificationCode(String verificationCode);
}
