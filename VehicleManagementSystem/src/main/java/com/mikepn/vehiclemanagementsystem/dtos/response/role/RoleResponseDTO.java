package com.mikepn.vehiclemanagementsystem.dtos.response.role;


import com.mikepn.vehiclemanagementsystem.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RoleResponseDTO {
    private Role role;
}