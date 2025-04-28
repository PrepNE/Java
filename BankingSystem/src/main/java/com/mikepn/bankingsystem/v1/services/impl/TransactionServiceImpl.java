package com.mikepn.bankingsystem.v1.services.impl;

import com.mikepn.bankingsystem.v1.dto.request.transaction.CreateTransactionDTO;
import com.mikepn.bankingsystem.v1.dto.request.transaction.DepositOrWithDrawDTO;
import com.mikepn.bankingsystem.v1.enums.ETransactionType;
import com.mikepn.bankingsystem.v1.enums.IEmailTemplate;
import com.mikepn.bankingsystem.v1.exceptions.BadRequestException;
import com.mikepn.bankingsystem.v1.exceptions.NotFoundException;
import com.mikepn.bankingsystem.v1.models.Account;
import com.mikepn.bankingsystem.v1.models.Customer;
import com.mikepn.bankingsystem.v1.models.Transaction;
import com.mikepn.bankingsystem.v1.repositories.IAccountRepository;
import com.mikepn.bankingsystem.v1.repositories.ICustomerRepository;
import com.mikepn.bankingsystem.v1.repositories.ITransactionRepository;
import com.mikepn.bankingsystem.v1.services.ITransactionService;
import com.mikepn.bankingsystem.v1.services.IUserService;
import com.mikepn.bankingsystem.v1.standalone.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;
    private final EmailService mailService;
    private final IUserService userService;
    private final ICustomerRepository customerRepository;


    @Override
    @Transactional
    public Transaction transfer(CreateTransactionDTO dto) {

        Account sourceAccount;

        if(dto.getSourceAccountNumber() != null) {
            sourceAccount = accountRepository.findByAccountNumber(dto.getSourceAccountNumber())
                    .orElseThrow(() -> new NotFoundException("Account not found"));
        } else {
            Customer senderCustomer = customerRepository.findByProfile(userService.getLoggedInUser())
                    .orElseThrow(() -> new NotFoundException("Customer not found"));

            sourceAccount = accountRepository.findByOwner(senderCustomer)
                    .orElseThrow(() -> new NotFoundException("Account not found"));
        }

        Account destAccount = accountRepository.findByAccountNumber(dto.getDestinationAccountNumber())
                .orElseThrow(() -> new NotFoundException("Destination Account not found"));

        if(dto.getAmount() <= 0){
            throw new BadRequestException("Transaction amount must be greater than zero");
        }

        if (sourceAccount.getId().equals(destAccount.getId())) {
            throw new BadRequestException("Cannot transfer to the same account");
        }

        if(sourceAccount.getBalance() < dto.getAmount()){
            throw new BadRequestException("Insufficient funds");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - dto.getAmount());
        destAccount.setBalance(destAccount.getBalance() + dto.getAmount());

        accountRepository.save(sourceAccount);
        accountRepository.save(destAccount);

        Transaction transaction = Transaction.builder()
                .account(sourceAccount)
                .destination(destAccount)
                .amount(dto.getAmount())
                .transactionType(ETransactionType.TRANSFER)
                .build();

        sendTransactionEmail(sourceAccount.getOwner(), transaction);
        sendTransactionEmail(destAccount.getOwner(), transaction);

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction deposit(DepositOrWithDrawDTO dto) {

        Account account;
        if(dto.getAccountNumber() != null) {
            account = accountRepository.findByAccountNumber(dto.getAccountNumber())
                    .orElseThrow(() -> new NotFoundException("Account not found"));
        } else {
            Customer customer = customerRepository.findByProfile(userService.getLoggedInUser())
                    .orElseThrow(() -> new NotFoundException("Customer not found"));

            account = accountRepository.findByOwner(customer)
                    .orElseThrow(() -> new NotFoundException("Account not found"));
        }


        if(dto.getAmount() <= 0){
            throw new BadRequestException("Transaction amount must be greater than zero");
        }

        account.setBalance(account.getBalance() + dto.getAmount());
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(dto.getAmount())
                .transactionType(ETransactionType.DEPOSIT)
                .build();
        Transaction savedTransaction = transactionRepository.save(transaction);
        sendTransactionEmail(account.getOwner(), savedTransaction);
        return savedTransaction;

    }

    @Override
    public Transaction withdraw(DepositOrWithDrawDTO dto) {
        Account account;
        if(dto.getAccountNumber() != null) {
            account = accountRepository.findByAccountNumber(dto.getAccountNumber())
                    .orElseThrow(() -> new NotFoundException("Account not found"));
        } else {
            Customer customer = customerRepository.findByProfile(userService.getLoggedInUser())
                    .orElseThrow(() -> new NotFoundException("Customer not found"));

            account = accountRepository.findByOwner(customer)
                    .orElseThrow(() -> new NotFoundException("Account not found"));
        }

        if(dto.getAmount() <= 0){
            throw new BadRequestException("Transaction amount must be greater than zero");
        }

        account.setBalance(account.getBalance() - dto.getAmount());
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(dto.getAmount())
                .transactionType(ETransactionType.WITHDRAWAL)
                .build();
        Transaction savedTransaction = transactionRepository.save(transaction);
        sendTransactionEmail(account.getOwner(), savedTransaction);
        return savedTransaction;

    }




    @Override
    public Account getMyAccount() {
        Customer customer = customerRepository.findByProfile(userService.getLoggedInUser())
                .orElseThrow(() -> new NotFoundException("Customer not found"));
        Account account = accountRepository.findByOwner(customer)
                .orElseThrow(() -> new NotFoundException("Account not found"));
        return account;
    }

    private void sendTransactionEmail(Customer customer, Transaction transaction) {
        try {
            Map<String, Object> variables = new HashMap<>();

            String fullName = customer.getProfile().getFullName();
            System.out.println("Sending email to: " + fullName);

            variables.put("username", fullName);
            variables.put("transaction", transaction);
            variables.put("transactionId", transaction.getId()); 
            variables.put("amount", transaction.getAmount());
            variables.put("type", transaction.getTransactionType().name());

            if (transaction.getTransactionType() == ETransactionType.DEPOSIT) {
                variables.put("sourceAccountNumber", transaction.getAccount().getAccountNumber());
                variables.put("destinationAccountNumber", transaction.getAccount().getAccountNumber());
            } else {
                if (transaction.getAccount() != null) {
                    variables.put("sourceAccountNumber", transaction.getAccount().getAccountNumber());
                }
                if (transaction.getDestination() != null) {
                    variables.put("destinationAccountNumber", transaction.getDestination().getAccountNumber());
                }
            }

            mailService.sendEmail(
                    customer.getProfile().getEmail(),
                    fullName,
                    "Transaction Notification",
                    IEmailTemplate.TRANSACTION_NOTIFICATION, // assuming this template exists
                    variables
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
