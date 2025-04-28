package com.mikepn.bankingsystem.v1.dto.response.customer;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerResponseDTO {
    private UUID id;
    private String fullName;
    private String email;
    private String dob;
    private boolean verified;
    private String accountNumber;
    private double balance;


}