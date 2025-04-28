package com.mikepn.bankingsystem.v1.dto.request.user;

import com.mikepn.bankingsystem.v1.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private User user;
}