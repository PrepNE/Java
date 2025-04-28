package com.mikepn.vehiclemanagementsystem.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mikepn.vehiclemanagementsystem.common.AbstractEntity;
import com.mikepn.vehiclemanagementsystem.enums.EPlateStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plate_numbers")
public class PlateNumber extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @Column(unique = true)
    private String plateNumber;

    private LocalDateTime issuedDate;

    @Enumerated(EnumType.STRING)
    private EPlateStatus plateStatus;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private Owner owner;


    @OneToOne(mappedBy = "currentPlate")
    @JsonIgnore
    private Vehicle vehicle;

    @OneToMany(mappedBy = "plateNumber", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OwnershipRecord> ownershipRecords;

}
