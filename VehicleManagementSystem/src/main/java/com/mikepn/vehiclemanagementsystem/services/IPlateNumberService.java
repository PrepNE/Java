package com.mikepn.vehiclemanagementsystem.services;

import com.mikepn.vehiclemanagementsystem.models.PlateNumber;

import java.util.List;
import java.util.UUID;

public interface IPlateNumberService {
    PlateNumber generateAndAssignPlateNumberToOwner(UUID ownerId);

    List<PlateNumber> getAllPlateNumbers();
}
