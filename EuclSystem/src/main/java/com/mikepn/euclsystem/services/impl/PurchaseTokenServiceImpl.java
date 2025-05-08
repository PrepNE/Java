package com.mikepn.euclsystem.services.impl;

import com.mikepn.euclsystem.dtos.requests.token.PurchaseTokenRequestDTO;
import com.mikepn.euclsystem.dtos.response.token.PurchaseTokenResponseDTO;
import com.mikepn.euclsystem.enums.ETokenStatus;
import com.mikepn.euclsystem.exceptions.NotFoundException;
import com.mikepn.euclsystem.models.Meter;
import com.mikepn.euclsystem.models.PurchasedToken;
import com.mikepn.euclsystem.repositories.IMeterRepository;
import com.mikepn.euclsystem.repositories.IPurchasedTokenRepository;
import com.mikepn.euclsystem.services.IPurchaseTokenService;
import com.mikepn.euclsystem.utils.helpers.TokenGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseTokenServiceImpl implements IPurchaseTokenService {

    private final IPurchasedTokenRepository purchasedTokenRepository;
    private final IMeterRepository meterRepository;
    private final TokenGeneratorUtil tokenGeneratorUtil;

    @Override
    public PurchaseTokenResponseDTO purchaseToken(PurchaseTokenRequestDTO dto) {

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

        Meter meter = meterRepository.findByMeterNumber(dto.getMeter())
                .orElseThrow(() -> new NotFoundException("Meter not found"));

        String token = tokenGeneratorUtil.generateUniqueToken();

        PurchasedToken purchasedToken = PurchasedToken.builder()
                .token(token)
                .meter(meter)
                .amount(dto.getAmount())
                .status(ETokenStatus.NEW)
                .tokenValueDays(days)
                .purchaseDate(LocalDateTime.now())
                .expirationDate(LocalDateTime.now().plusMinutes(1))
                .build();

        purchasedTokenRepository.save(purchasedToken);

        return PurchaseTokenResponseDTO.builder()
                .meterNumber(purchasedToken.getMeter().getMeterNumber())
                .token(purchasedToken.getToken())
                .amount(purchasedToken.getAmount())
                .tokenValueInDays(purchasedToken.getTokenValueDays())
                .purchaseDate(purchasedToken.getPurchaseDate())
                .build();
    }

    @Override
    public List<PurchaseTokenResponseDTO> getAllTokensByMeterNumber(String meterNumber) {
        List<PurchasedToken> tokens = purchasedTokenRepository.findByMeter_MeterNumber(meterNumber);
        return tokens.stream()
                .map(token ->PurchaseTokenResponseDTO.builder()
                        .meterNumber(token.getMeter().getMeterNumber())
                        .token(token.getToken())
                        .amount(token.getAmount())
                        .tokenValueInDays(token.getTokenValueDays())
                        .purchaseDate(token.getPurchaseDate())
                        .build())
                .toList();
    }
}
