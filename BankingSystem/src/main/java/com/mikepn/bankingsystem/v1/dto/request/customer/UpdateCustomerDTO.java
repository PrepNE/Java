package com.mikepn.bankingsystem.v1.dto.request.customer;

import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateCustomerDTO {

    private String firstName;

    private String lastName;

    @Email(message = "Invalid email")
    private String email;

    private String phoneNumber;

    private LocalDate dateOfBirth;

}