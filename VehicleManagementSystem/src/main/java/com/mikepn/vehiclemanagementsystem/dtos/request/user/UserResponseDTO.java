package com.mikepn.vehiclemanagementsystem.dtos.request.user;


import com.mikepn.vehiclemanagementsystem.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private User user;
}