package com.mikepn.vehiclemanagementsystem.controllers;


import com.mikepn.vehiclemanagementsystem.models.PlateNumber;
import com.mikepn.vehiclemanagementsystem.payload.ApiResponse;
import com.mikepn.vehiclemanagementsystem.services.IPlateNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/plate-numbers")
@RequiredArgsConstructor
@Tag(name="Plate Numbers")
public class PlateNumberController {

    private final IPlateNumberService plateNumberService;

    @PostMapping("/generate/{ownerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PlateNumber>> assignPlateToOwner(@PathVariable UUID ownerId){
        try{
            PlateNumber plateNumber = plateNumberService.generateAndAssignPlateNumberToOwner(ownerId);
            return ApiResponse.success("Plate number assigned successfully", HttpStatus.OK, plateNumber);
        }catch (Exception e){
            return ApiResponse.fail("Failed to assign plate number to owner: " , HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping
    @Operation(summary = "Get All Plate Numbers Available")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<PlateNumber>>> getAllPlateNumbers(){
        try{
            List<PlateNumber> plateNumbers = plateNumberService.getAllPlateNumbers();
            return ApiResponse.success("Plate numbers retrieved successfully", HttpStatus.OK, plateNumbers);
        }catch (Exception e){
            return ApiResponse.fail("Failed to retrieve plate numbers", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
