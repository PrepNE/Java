package com.mikepn.vehiclemanagementsystem.services.impl;

import com.mikepn.vehiclemanagementsystem.enums.EPlateStatus;
import com.mikepn.vehiclemanagementsystem.exceptions.NotFoundException;
import com.mikepn.vehiclemanagementsystem.models.Owner;
import com.mikepn.vehiclemanagementsystem.models.PlateNumber;
import com.mikepn.vehiclemanagementsystem.repositories.IOwnerRepository;
import com.mikepn.vehiclemanagementsystem.repositories.IPlateNumberRepository;
import com.mikepn.vehiclemanagementsystem.services.IPlateNumberService;
import com.mikepn.vehiclemanagementsystem.utils.helpers.PlateNumberGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlateNumberServiceImpl implements IPlateNumberService {

    private final IPlateNumberRepository plateNumberRepository;
    private final IOwnerRepository ownerRepository;
    private final PlateNumberGenerator plateNumberGenerator;

    @Override
    @Transactional
    public PlateNumber generateAndAssignPlateNumberToOwner(UUID ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException("Owner not found with ID: " + ownerId));

        String plateValue = plateNumberGenerator.generateNextPlateNumber();

        PlateNumber plateNumber = PlateNumber.builder()
                .owner(owner)
                .plateNumber(plateValue)
                .plateStatus(EPlateStatus.AVAILABLE)
                .issuedDate(LocalDateTime.now())
                .build();

        return plateNumberRepository.save(plateNumber);
    }

    @Override
    public List<PlateNumber> getAllPlateNumbers() {
        return plateNumberRepository.findAll();
    }
}
