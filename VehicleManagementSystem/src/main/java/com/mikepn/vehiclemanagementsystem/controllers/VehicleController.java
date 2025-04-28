package com.mikepn.vehiclemanagementsystem.controllers;


import com.mikepn.vehiclemanagementsystem.dtos.request.vehicle.CreateVehicleDTO;
import com.mikepn.vehiclemanagementsystem.dtos.request.vehicle.UpdateVehicleDTO;
import com.mikepn.vehiclemanagementsystem.dtos.response.vehicle.PaginatedResponse;
import com.mikepn.vehiclemanagementsystem.dtos.response.vehicle.VehicleResponseDTO;
import com.mikepn.vehiclemanagementsystem.payload.ApiResponse;
import com.mikepn.vehiclemanagementsystem.services.IVehicleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("vehicles")
@RequiredArgsConstructor
@Tag(name="Vehicles")
public class VehicleController {

    private final IVehicleService vehicleService;


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> addVehicle(@Valid @RequestBody CreateVehicleDTO dto){
        try{
            VehicleResponseDTO response = vehicleService.createVehicle(dto);
            return ApiResponse.success("Vehicle Create successfully", HttpStatus.CREATED, response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to create vehicle", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> getVehicleById(@PathVariable UUID id){
        try{
            VehicleResponseDTO response = vehicleService.getVehicle(id);
            return ApiResponse.success("Vehicle Get successfully", HttpStatus.OK, response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to get vehicle", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> searchVehicle(@RequestParam String keyword ){
        try{
            VehicleResponseDTO response = vehicleService.searchVehicle(keyword);
            return ApiResponse.success("Vehicle Get successfully", HttpStatus.OK, response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to get vehicle", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<VehicleResponseDTO>> updateVehicle(@PathVariable UUID id, @Valid @RequestBody UpdateVehicleDTO dto){
        try{
            VehicleResponseDTO response = vehicleService.updateVehicle(id, dto);
            return ApiResponse.success("Vehicle Update successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to update vehicle", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Page<VehicleResponseDTO>>>> getAllVehicles(Pageable pageable){
        try{
            List<Page<VehicleResponseDTO>> response = vehicleService.getAllVehicles(pageable);
            return ApiResponse.success("Vehicles Retrieved Successfully", HttpStatus.OK , response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to get vehicles", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<ApiResponse<PaginatedResponse<VehicleResponseDTO>>> getVehiclesByOwner(
            @PathVariable UUID ownerId,
            @PageableDefault(size = 10) Pageable pageable) {

        try{
            Page<VehicleResponseDTO> page = vehicleService.getAllVehiclesByOwnerId(ownerId, pageable);

            PaginatedResponse<VehicleResponseDTO> response = PaginatedResponse.<VehicleResponseDTO>builder()
                    .page(page.getNumber())
                    .size(page.getSize())
                    .totalPages(page.getTotalPages())
                    .totalElements(page.getTotalElements())
                    .numberOfElements(page.getNumberOfElements())
                    .first(page.isFirst())
                    .last(page.isLast())
                    .vehicles(page.getContent())
                    .build();

            return ApiResponse.success("Vehicles retrieved successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to retrieve vehicles", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteVehicle(@PathVariable UUID id){
        try{
            vehicleService.deleteVehicle(id);
            return ApiResponse.success("Vehicle Deleted Successfully", HttpStatus.NO_CONTENT, null);
        }catch (Exception e){
            return ApiResponse.fail("Failed to delete vehicle", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
