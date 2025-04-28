package com.mikepn.bankingsystem.v1.dto.request.transaction;

import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositOrWithDrawDTO {

    @Positive(message = "Amount must be greater than zero")
    private double amount;
    private String accountNumber;
}