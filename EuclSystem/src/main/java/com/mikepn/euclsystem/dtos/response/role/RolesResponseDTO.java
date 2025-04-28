package com.mikepn.euclsystem.dtos.response.role;


import com.mikepn.euclsystem.models.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@Builder
public class RolesResponseDTO {
    private Page<Role> roles;
}