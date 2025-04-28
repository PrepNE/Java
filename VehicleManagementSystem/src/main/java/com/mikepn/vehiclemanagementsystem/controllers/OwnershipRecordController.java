package com.mikepn.vehiclemanagementsystem.controllers;


import com.mikepn.vehiclemanagementsystem.dtos.request.vehicle.TransferVehicleDTO;
import com.mikepn.vehiclemanagementsystem.dtos.response.vehicle.VehicleOwnershipResponseDTO;
import com.mikepn.vehiclemanagementsystem.payload.ApiResponse;
import com.mikepn.vehiclemanagementsystem.services.IOwnershipRecordService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ownership-records")
@RequiredArgsConstructor
@Tag(name="Ownership Records")
public class OwnershipRecordController {

    private final IOwnershipRecordService ownershipRecordService;


    @PostMapping("transfer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> transferOwnership(@Valid @RequestBody TransferVehicleDTO dto){
        try {
            ownershipRecordService.transferVehicleOwnership(dto);
            return ApiResponse.success("Successful.", HttpStatus.CREATED , "Vehicle ownership transferred successfully.");
        }catch (Exception e){
            return ApiResponse.fail("Failed to transfer ownership", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/history/chassis/{chassisNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<VehicleOwnershipResponseDTO>>> getOwnershipHistoryByChassis(@PathVariable String chassisNumber) {
        try {
            List<VehicleOwnershipResponseDTO> response = ownershipRecordService.getOwnershipHistoryByChassis(chassisNumber);
            return  ApiResponse.success("Retrieved ownership records successfully", HttpStatus.OK , response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to get ownership history by chassis number", HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("/history/plate/{plateNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<VehicleOwnershipResponseDTO>>> getOwnershipHistoryByPlate(@PathVariable String plateNumber) {
        try{
            List<VehicleOwnershipResponseDTO> response = ownershipRecordService.getOwnershipHistoryByPlate(plateNumber);
            return ApiResponse.success("Retrieved ownership records successfully", HttpStatus.OK , response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to get ownership history by plate number", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
