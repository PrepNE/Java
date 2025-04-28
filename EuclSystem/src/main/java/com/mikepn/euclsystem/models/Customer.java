package com.mikepn.euclsystem.models;


import com.mikepn.euclsystem.common.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @OneToOne
    @JoinColumn(name = "profile_id")
    private User profile;


    @OneToMany(mappedBy = "customer")
    private List<Meter> meters;

}
