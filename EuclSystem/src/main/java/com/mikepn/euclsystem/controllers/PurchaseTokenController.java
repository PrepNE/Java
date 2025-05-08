package com.mikepn.euclsystem.controllers;


import com.mikepn.euclsystem.dtos.requests.token.PurchaseTokenRequestDTO;
import com.mikepn.euclsystem.dtos.response.token.PurchaseTokenResponseDTO;
import com.mikepn.euclsystem.payload.ApiResponse;
import com.mikepn.euclsystem.services.IPurchaseTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("purchase-tokens")
@RequiredArgsConstructor
@Tag(name = "Purchase Token")
public class PurchaseTokenController {

    private  final IPurchaseTokenService purchaseTokenService;


    @PostMapping("/purchase")
    public ResponseEntity<ApiResponse<PurchaseTokenResponseDTO>> purchaseToken(PurchaseTokenRequestDTO dto) {
        try{
            PurchaseTokenResponseDTO response = purchaseTokenService.purchaseToken(dto);
            return ApiResponse.success("Purchase token purchased successfully", HttpStatus.OK, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to purchase token", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping("/meter/tokens")
    @Operation(summary = "Get All tokens by meter number")
    public ResponseEntity<ApiResponse<List<PurchaseTokenResponseDTO>>> getAllTokensByMeterNumber(String meterNumber) {
        try{
            List<PurchaseTokenResponseDTO>  response = purchaseTokenService.getAllTokensByMeterNumber(meterNumber);
            return ApiResponse.success("All tokens retrieved successfully", HttpStatus.OK, response);
        }catch (Exception e) {
            return ApiResponse.fail("Failed to retrieve all tokens from that meter", HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }
}
