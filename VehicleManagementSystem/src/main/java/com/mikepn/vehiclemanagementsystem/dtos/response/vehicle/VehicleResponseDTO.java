package com.mikepn.vehiclemanagementsystem.dtos.response.vehicle;

import lombok.Data;

@Data
public class VehicleResponseDTO {

    private String manufacturer;
    private int manufacturedYear;
    private String model;
    private double price;
}
