package com.mikepn.euclsystem.dtos.response.role;



import com.mikepn.euclsystem.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RoleResponseDTO {
    private Role role;
}