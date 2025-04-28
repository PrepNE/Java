package com.mikepn.bankingsystem.v1.services;

import com.mikepn.bankingsystem.v1.dto.request.transaction.CreateTransactionDTO;
import com.mikepn.bankingsystem.v1.dto.request.transaction.DepositOrWithDrawDTO;
import com.mikepn.bankingsystem.v1.models.Account;
import com.mikepn.bankingsystem.v1.models.Transaction;

public interface ITransactionService {
    public Transaction transfer(CreateTransactionDTO dto);
    public Transaction deposit(DepositOrWithDrawDTO dto);
    public Transaction withdraw(DepositOrWithDrawDTO dto);

    Account getMyAccount();
}
