package com.mikepn.bankingsystem.v1.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mikepn.bankingsystem.v1.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    private double balance = 0.0;

    @Column(unique = true, nullable = false)
    private String accountNumber;


    @OneToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Customer owner;

    public Account(Customer owner) {
        this.owner = owner;
    }


}

