package com.mikepn.vehiclemanagementsystem.dtos.response.auth;

import com.mikepn.vehiclemanagementsystem.models.User;
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
