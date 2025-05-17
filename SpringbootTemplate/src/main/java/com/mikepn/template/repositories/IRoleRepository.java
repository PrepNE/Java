package com.mikepn.template.repositories;

import com.mikepn.template.enums.ERole;
import com.mikepn.template.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IRoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findRoleByName(ERole name);

    boolean existsByName(String name);
}
