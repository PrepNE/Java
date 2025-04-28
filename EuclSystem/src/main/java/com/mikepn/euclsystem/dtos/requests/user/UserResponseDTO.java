package com.mikepn.euclsystem.dtos.requests.user;



import com.mikepn.euclsystem.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private User user;
}