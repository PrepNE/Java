package com.mikepn.vehiclemanagementsystem.dtos.request.vehicle;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateVehicleDTO {

    private UUID ownerId;
    private UUID plateId;
    private String manufacturer;
    private int manufacturedYear;
    private String model;
    private double price;
}
