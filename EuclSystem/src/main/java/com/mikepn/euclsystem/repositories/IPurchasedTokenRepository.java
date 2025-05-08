package com.mikepn.euclsystem.repositories;

import com.mikepn.euclsystem.models.PurchasedToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPurchasedTokenRepository extends JpaRepository<PurchasedToken, UUID> {

    Optional<PurchasedToken> findByToken(String token);
    List<PurchasedToken> findByMeter_MeterNumber(String meterNumber);
}
