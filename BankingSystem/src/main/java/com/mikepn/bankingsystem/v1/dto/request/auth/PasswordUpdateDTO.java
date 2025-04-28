package com.mikepn.bankingsystem.v1.dto.request.auth;

import com.mikepn.bankingsystem.v1.annotations.ValidPassword;
import lombok.Data;

@Data
public class PasswordUpdateDTO {
    private String oldPassword;
    @ValidPassword(message = "Password should be strong")
    private String newPassword;
}