package com.mikepn.bankingsystem.v1.repositories;

import com.mikepn.bankingsystem.v1.models.Account;
import com.mikepn.bankingsystem.v1.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAccountRepository extends JpaRepository<Account, UUID> {

    public boolean existsByOwner(Customer customer);
    public Optional<Account> findByOwner(Customer customer);
    public Optional<Account> findByAccountNumber(String accountNumber);
}
