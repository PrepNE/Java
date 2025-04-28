package com.mikepn.bankingsystem.v1.models;

import com.mikepn.bankingsystem.v1.common.AbstractEntity;
import com.mikepn.bankingsystem.v1.enums.ETransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;


@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetail extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private double amount;
    @Enumerated(EnumType.STRING)
    private ETransactionType transactionType;
}
