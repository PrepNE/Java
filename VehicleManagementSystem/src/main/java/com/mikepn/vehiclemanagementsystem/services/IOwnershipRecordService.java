package com.mikepn.vehiclemanagementsystem.services;

import com.mikepn.vehiclemanagementsystem.dtos.request.vehicle.TransferVehicleDTO;
import com.mikepn.vehiclemanagementsystem.dtos.response.vehicle.VehicleOwnershipResponseDTO;

import java.util.List;

public interface IOwnershipRecordService {

    void transferVehicleOwnership(TransferVehicleDTO dto);
    List<VehicleOwnershipResponseDTO> getOwnershipHistoryByChassis(String chassisNumber);
    List<VehicleOwnershipResponseDTO> getOwnershipHistoryByPlate(String plateNumber);


}
