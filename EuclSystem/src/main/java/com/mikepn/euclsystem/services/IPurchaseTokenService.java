package com.mikepn.euclsystem.services;

import com.mikepn.euclsystem.dtos.requests.token.PurchaseTokenRequestDTO;
import com.mikepn.euclsystem.dtos.response.token.PurchaseTokenResponseDTO;

import java.util.List;

public interface IPurchaseTokenService {

    PurchaseTokenResponseDTO purchaseToken(PurchaseTokenRequestDTO dto);
    List<PurchaseTokenResponseDTO> getAllTokensByMeterNumber(String meterNumber);
}
