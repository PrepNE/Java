package com.mikepn.vehiclemanagementsystem.models;


import com.mikepn.vehiclemanagementsystem.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ownership_records")
public class OwnershipRecord extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private double purchasePrice;
    private LocalDateTime transferDate;


    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private PlateNumber plateNumber;
}
