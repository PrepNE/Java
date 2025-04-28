package com.mikepn.euclsystem.services;

import com.mikepn.euclsystem.dtos.requests.role.CreateRoleDTO;
import com.mikepn.euclsystem.dtos.response.role.RoleResponseDTO;
import com.mikepn.euclsystem.dtos.response.role.RolesResponseDTO;
import com.mikepn.euclsystem.enums.ERole;
import com.mikepn.euclsystem.models.Role;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IRoleService {
    public Role getRoleById(UUID roleId);

    public Role getRoleByName(ERole roleName);

    public void createRole(ERole roleName);

    public RoleResponseDTO createRole(CreateRoleDTO createRoleDTO);

    public RolesResponseDTO getRoles(Pageable pageable);

    public void deleteRole(UUID roleId);

    public boolean isRolePresent(ERole roleName);
}