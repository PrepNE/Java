package com.mikepn.euclsystem.dtos.requests.auth;


import com.mikepn.euclsystem.annotations.ValidPassword;
import lombok.Data;

@Data
public class PasswordResetDTO {
    private String email;
    private String resetCode;

    @ValidPassword(message = "Password should be strong")
    private String newPassword;
}