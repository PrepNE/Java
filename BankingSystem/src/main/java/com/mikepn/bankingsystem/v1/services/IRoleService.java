package com.mikepn.bankingsystem.v1.services;

import com.mikepn.bankingsystem.v1.dto.request.role.CreateRoleDTO;
import com.mikepn.bankingsystem.v1.dto.response.role.RoleResponseDTO;
import com.mikepn.bankingsystem.v1.dto.response.role.RolesResponseDTO;
import com.mikepn.bankingsystem.v1.enums.ERole;
import com.mikepn.bankingsystem.v1.models.Role;
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