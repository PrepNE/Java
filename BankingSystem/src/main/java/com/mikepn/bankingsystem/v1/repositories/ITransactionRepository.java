package com.mikepn.bankingsystem.v1.repositories;

import com.mikepn.bankingsystem.v1.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ITransactionRepository extends JpaRepository<Transaction, UUID> {

}

