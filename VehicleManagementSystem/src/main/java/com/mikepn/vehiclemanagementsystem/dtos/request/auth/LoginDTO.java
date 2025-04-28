package com.mikepn.vehiclemanagementsystem.dtos.request.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String email;
    private String password;
}
