package com.mikepn.vehiclemanagementsystem.models;

import com.mikepn.vehiclemanagementsystem.common.AbstractEntity;
import jakarta.persistence.*;
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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String chassisNumber;

    private String manufacturer;
    private int manufacturedYear;
    private String model;
    private double price;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;


    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<OwnershipRecord> ownershipRecords;

    @OneToOne
    @JoinColumn(name = "current_plate_id")
    private PlateNumber currentPlate;
}
