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

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private User profile;


    private String address;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<PlateNumber> plateNumbers;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<OwnershipRecord> ownershipRecords;


}
