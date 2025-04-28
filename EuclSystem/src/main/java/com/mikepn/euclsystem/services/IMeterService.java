package com.mikepn.euclsystem.services;

import com.mikepn.euclsystem.dtos.requests.meter.CreateMeterDTO;
import com.mikepn.euclsystem.dtos.response.meter.MeterResponseDTO;

public interface IMeterService {

    MeterResponseDTO createMeter(CreateMeterDTO dto);
}
