package com.mikepn.vehiclemanagementsystem.services;

import com.mikepn.vehiclemanagementsystem.dtos.request.vehicle.CreateVehicleDTO;
import com.mikepn.vehiclemanagementsystem.dtos.request.vehicle.UpdateVehicleDTO;
import com.mikepn.vehiclemanagementsystem.dtos.response.vehicle.VehicleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IVehicleService {

    VehicleResponseDTO createVehicle(CreateVehicleDTO dto);

    VehicleResponseDTO getVehicle(UUID vehicleId);

    VehicleResponseDTO searchVehicle(String keyword);

    VehicleResponseDTO updateVehicle(UUID vehicleId, UpdateVehicleDTO dto);

    List<Page<VehicleResponseDTO>> getAllVehicles(Pageable pageable);

    Page<VehicleResponseDTO> getAllVehiclesByOwnerId(UUID ownerId, Pageable pageable);

    void deleteVehicle(UUID vehicleId);


}
