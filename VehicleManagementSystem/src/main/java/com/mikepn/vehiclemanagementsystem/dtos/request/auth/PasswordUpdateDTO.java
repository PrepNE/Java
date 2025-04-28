package com.mikepn.vehiclemanagementsystem.dtos.request.auth;


import com.mikepn.vehiclemanagementsystem.annotations.ValidPassword;
import lombok.Data;

@Data
public class PasswordUpdateDTO {
    private String oldPassword;
    @ValidPassword(message = "Password should be strong")
    private String newPassword;
}