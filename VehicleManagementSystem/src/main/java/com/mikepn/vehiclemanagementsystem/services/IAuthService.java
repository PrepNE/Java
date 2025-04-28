package com.mikepn.vehiclemanagementsystem.services;

import com.mikepn.vehiclemanagementsystem.dtos.request.auth.LoginDTO;
import com.mikepn.vehiclemanagementsystem.dtos.response.auth.AuthResponse;

public interface IAuthService {

    AuthResponse login(LoginDTO signInDTO);

    void forgotPassword(String email);

    void resetPassword(String email, String passwordResetCode, String newPassword);

    void initiateAccountVerificaton(String email);

    void verifyAccount(String verificationCode);

    void resendVerificationCode(String email);

    void updatePassword(String email, String oldPassword, String newPassword);
}
