package com.mikepn.bankingsystem.v1.dto.response.role;

import com.mikepn.bankingsystem.v1.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RoleResponseDTO {
    private Role role;
}