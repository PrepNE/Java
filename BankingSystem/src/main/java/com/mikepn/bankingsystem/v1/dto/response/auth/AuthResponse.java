package com.mikepn.bankingsystem.v1.dto.response.auth;

import com.mikepn.bankingsystem.v1.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private User user;


    public AuthResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
