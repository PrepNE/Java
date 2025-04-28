package com.mikepn.bankingsystem.v1.services;

import com.mikepn.bankingsystem.v1.dto.request.auth.LoginDTO;
import com.mikepn.bankingsystem.v1.dto.response.auth.AuthResponse;

public interface IAuthService {

    AuthResponse login(LoginDTO signInDTO);

    void forgotPassword(String email);

    void resetPassword(String email, String passwordResetCode, String newPassword);

    void initiateAccountVerificaton(String email);

    void verifyAccount(String verificationCode);

    void resendVerificationCode(String email);

    void updatePassword(String email, String oldPassword, String newPassword);
}
