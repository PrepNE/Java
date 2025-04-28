package com.mikepn.euclsystem.models;

import com.mikepn.euclsystem.common.AbstractEntity;
import com.mikepn.euclsystem.enums.ETokenStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "purchased_tokens")
public class PurchasedToken extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, length = 6,nullable = false)
    private String meter;


    @Column(unique = true, length = 16,nullable = false)
    private String token;

    @Enumerated(EnumType.STRING)
    private ETokenStatus status = ETokenStatus.NEW;

    private int tokenValueDays;

    private int amount;

    private LocalDateTime purchaseDate = LocalDateTime.now();
    private LocalDateTime expirationDate;
}
