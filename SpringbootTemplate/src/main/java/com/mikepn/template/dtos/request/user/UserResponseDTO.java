package com.mikepn.template.dtos.request.user;


import com.mikepn.template.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private User user;
}