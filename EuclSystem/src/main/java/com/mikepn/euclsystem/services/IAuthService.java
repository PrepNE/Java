package com.mikepn.euclsystem.services;

import com.mikepn.euclsystem.dtos.requests.auth.LoginDTO;
import com.mikepn.euclsystem.dtos.response.auth.AuthResponse;

public interface IAuthService {

    AuthResponse login(LoginDTO signInDTO);

    void forgotPassword(String email);

    void resetPassword(String email, String passwordResetCode, String newPassword);

    void initiateAccountVerificaton(String email);

    void verifyAccount(String verificationCode);

    void resendVerificationCode(String email);

    void updatePassword(String email, String oldPassword, String newPassword);
}
