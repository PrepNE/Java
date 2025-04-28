package com.mikepn.euclsystem.services.impl;

import com.mikepn.euclsystem.dtos.response.token.PurchaseTokenResponseDTO;
import com.mikepn.euclsystem.models.PurchasedToken;
import com.mikepn.euclsystem.repositories.IPurchasedTokenRepository;
import com.mikepn.euclsystem.services.IPurchaseTokenService;
import com.mikepn.euclsystem.utils.helpers.TokenGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class PurchaseTokenServiceImpl implements IPurchaseTokenService {

    private final IPurchasedTokenRepository purchasedTokenRepository;
    private final TokenGeneratorUtil tokenGeneratorUtil;

    @Override
    public PurchaseTokenResponseDTO purchaseToken(PurchaseTokenResponseDTO dto) {

        if(dto.getAmount() % 100 != 0){
            throw new IllegalArgumentException("Amount must be a multiple of 100");
        }

        if(dto.getAmount() < 100){
            throw new IllegalArgumentException("Amount must be greater than 100");
        }

        int days = dto.getAmount() / 100;
        if(days > 1825){
            throw new IllegalArgumentException("Token can not be valid for more than 5 years");
        }

        String token = tokenGeneratorUtil.generateUniqueToken();

        PurchasedToken purchasedToken = PurchasedToken.builder()
                .token(token)
                .meter(dto.getMeterNumber())
                .amount(dto.getAmount())
                .tokenValueDays(days)
                .expirationDate(LocalDateTime.now().plus(Period.ofDays(days)))
                .build();

        return PurchaseTokenResponseDTO.builder()
                .meterNumber(purchasedToken.getMeter())
                .token(purchasedToken.getToken())
                .amount(purchasedToken.getAmount())
                .tokenValueInDays(purchasedToken.getTokenValueDays())
                .purchaseDate(purchasedToken.getPurchaseDate())
                .build();
    }
}
