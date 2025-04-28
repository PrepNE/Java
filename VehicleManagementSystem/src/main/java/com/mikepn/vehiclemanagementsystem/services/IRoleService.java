package com.mikepn.vehiclemanagementsystem.services;


import com.mikepn.vehiclemanagementsystem.dtos.request.role.CreateRoleDTO;
import com.mikepn.vehiclemanagementsystem.dtos.response.role.RoleResponseDTO;
import com.mikepn.vehiclemanagementsystem.dtos.response.role.RolesResponseDTO;
import com.mikepn.vehiclemanagementsystem.enums.ERole;
import com.mikepn.vehiclemanagementsystem.models.Role;
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