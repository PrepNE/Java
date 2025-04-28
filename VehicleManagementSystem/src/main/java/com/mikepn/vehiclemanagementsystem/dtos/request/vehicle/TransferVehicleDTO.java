package com.mikepn.vehiclemanagementsystem.dtos.request.vehicle;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TransferVehicleDTO {
    private UUID vehicleId;
    private UUID newOwnerId;
    private UUID newPlateId;
    private double purchasePrice;
}
