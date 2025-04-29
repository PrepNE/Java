package com.mikepn.euclsystem.services;

import com.mikepn.euclsystem.dtos.requests.token.PurchaseTokenRequestDTO;
import com.mikepn.euclsystem.dtos.response.token.PurchaseTokenResponseDTO;

public interface IPurchaseTokenService {

    PurchaseTokenResponseDTO purchaseToken(PurchaseTokenRequestDTO dto);
}
