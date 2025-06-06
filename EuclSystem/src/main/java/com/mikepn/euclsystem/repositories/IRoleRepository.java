package com.mikepn.euclsystem.repositories;


import com.mikepn.euclsystem.enums.ERole;
import com.mikepn.euclsystem.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findRoleByName(ERole name);

    boolean existsByName(String name);
}
