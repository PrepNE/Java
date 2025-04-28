package com.mikepn.bankingsystem.v1.services.impl;

import com.mikepn.bankingsystem.v1.dto.request.role.CreateRoleDTO;
import com.mikepn.bankingsystem.v1.dto.response.role.RoleResponseDTO;
import com.mikepn.bankingsystem.v1.dto.response.role.RolesResponseDTO;
import com.mikepn.bankingsystem.v1.enums.ERole;
import com.mikepn.bankingsystem.v1.exceptions.InternalServerErrorException;
import com.mikepn.bankingsystem.v1.exceptions.NotFoundException;
import com.mikepn.bankingsystem.v1.models.Role;
import com.mikepn.bankingsystem.v1.repositories.IRoleRepository;
import com.mikepn.bankingsystem.v1.services.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepository roleRepository;

    @Override
    public Role getRoleById(UUID roleId) {
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("Role with ID " + roleId + " not found"));
    }

    @Override
    public Role getRoleByName(ERole roleName) {
        return roleRepository.findRoleByRoleName(roleName)
                .orElseThrow(() -> new NotFoundException("Role " + roleName + " not found"));
    }

    @Override
    public void createRole(ERole roleName) {
        if (roleRepository.findRoleByRoleName(roleName).isPresent()) {
            throw new InternalServerErrorException("Role " + roleName + " already exists");
        }

        Role role = Role.builder()
                .roleName(roleName)
                .build();

        try {
            roleRepository.save(role);
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not create role: " + e.getMessage());
        }
    }

    @Override
    public RoleResponseDTO createRole(CreateRoleDTO createRoleDTO) {
        ERole roleName = ERole.valueOf(createRoleDTO.getName().name().toUpperCase());

        if (roleRepository.findRoleByRoleName(roleName).isPresent()) {
            throw new InternalServerErrorException("Role " + roleName + " already exists");
        }

        Role role = Role.builder()
                .roleName(roleName)
                .build();

        try {
            Role savedRole = roleRepository.save(role);
            return RoleResponseDTO.builder()
                     .role(savedRole)
                    .build();
        } catch (Exception e) {
            throw new InternalServerErrorException("Could not create role: " + e.getMessage());
        }
    }

    @Override
    public RolesResponseDTO getRoles(Pageable pageable) {
        try {
            Page<Role> rolePage = roleRepository.findAll(pageable);
            return RolesResponseDTO.builder()
                    .roles(rolePage)
                    .build();
        } catch (Exception e) {
            throw new InternalServerErrorException("Failed to fetch roles: " + e.getMessage());
        }
    }



    @Override
    public void deleteRole(UUID roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NotFoundException("The role is not present"));

        try {
            roleRepository.delete(role);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public boolean isRolePresent(ERole roleName) {
        try {
            return roleRepository.findRoleByRoleName(roleName).isPresent();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
