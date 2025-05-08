package com.mikepn.euclsystem.services;

import com.mikepn.euclsystem.dtos.requests.meter.CreateMeterDTO;
import com.mikepn.euclsystem.dtos.response.meter.MeterResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IMeterService {

    MeterResponseDTO createMeter(CreateMeterDTO dto);
    List<MeterResponseDTO> findMetersByCustomer(UUID customerId);
}
