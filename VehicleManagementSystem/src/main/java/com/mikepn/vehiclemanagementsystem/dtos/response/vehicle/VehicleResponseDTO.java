package com.mikepn.vehiclemanagementsystem.dtos.response.vehicle;

import lombok.Data;

import java.util.UUID;

@Data
public class VehicleResponseDTO {

    private UUID id;
    private String manufacturer;
    private int manufacturedYear;
    private String model;
    private double price;
}
