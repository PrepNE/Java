package com.mikepn.vehiclemanagementsystem.controllers;


import com.mikepn.vehiclemanagementsystem.dtos.request.owner.CreateOwnerDTO;
import com.mikepn.vehiclemanagementsystem.dtos.request.owner.UpdateOwnerDTO;
import com.mikepn.vehiclemanagementsystem.dtos.response.owner.OwnerResponseDTO;
import com.mikepn.vehiclemanagementsystem.models.Owner;
import com.mikepn.vehiclemanagementsystem.payload.ApiResponse;
import com.mikepn.vehiclemanagementsystem.services.IOwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("owners")
@RequiredArgsConstructor
@Tag(name="Owner")
public class OwnerController {

    private final IOwnerService ownerService;



    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<OwnerResponseDTO>>  createOwner(@Valid @RequestBody CreateOwnerDTO dto) {
        try{
            OwnerResponseDTO response = ownerService.createOwner(dto);
            return ApiResponse.success("Owner created successfully", HttpStatus.CREATED, response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to create owner", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Owner>> getOwner(@PathVariable UUID id) {
        try{
            Owner response = ownerService.getOwnerById(id);
            return ApiResponse.success("Owner found", HttpStatus.OK, response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to get owner", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/plate-number/ownerId")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STANDARD')")
    public ResponseEntity<ApiResponse<List<String>>> getPlateNumbersByOwnerId(@RequestParam UUID ownerId) {
        try{
            List<String> response = ownerService.getPlateNumbersByOwnerId(ownerId);
            return ApiResponse.success("Owner found", HttpStatus.OK, response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to get owner", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("/search")
    @Operation(summary = "Search Owner by email, national id and phone number", description = "It returns Owner")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<OwnerResponseDTO>> searchOwner(@RequestParam String keyword) {
        try{
            OwnerResponseDTO response = ownerService.searchOwner(keyword);
            return ApiResponse.success("Owner found", HttpStatus.OK, response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to get owner", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Page<OwnerResponseDTO>>> getAllOwner(Pageable pageable) {
        try {
            Page<OwnerResponseDTO> response = ownerService.getAllOwners(pageable);
            return ApiResponse.success("Owner found", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to get owner", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }



    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Owner>> updateOwner(@PathVariable UUID id, @Valid @RequestBody UpdateOwnerDTO dto) {
        try{
            Owner response = ownerService.updateOwner(id, dto);
            return ApiResponse.success("Owner updated successfully", HttpStatus.OK, response);
        }catch (Exception e){
            return ApiResponse.fail("Failed to update owner", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteOwner(@PathVariable UUID id) {
        try{
            ownerService.deleteOwner(id);
            return ApiResponse.success("Owner deleted successfully", HttpStatus.NO_CONTENT, null);
        }catch (Exception e){
            return ApiResponse.fail("Failed to delete owner", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
