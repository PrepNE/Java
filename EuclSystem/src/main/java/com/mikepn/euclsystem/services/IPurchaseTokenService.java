package com.mikepn.euclsystem.services;

import com.mikepn.euclsystem.dtos.response.token.PurchaseTokenResponseDTO;

public interface IPurchaseTokenService {

    PurchaseTokenResponseDTO purchaseToken(PurchaseTokenResponseDTO dto);
}
