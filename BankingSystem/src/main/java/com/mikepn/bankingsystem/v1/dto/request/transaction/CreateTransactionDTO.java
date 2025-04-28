package com.mikepn.bankingsystem.v1.dto.request.transaction;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateTransactionDTO {
    private double amount;
    private String destinationAccountNumber;
    private String sourceAccountNumber;
}
