package com.mikepn.euclsystem.services.impl;

import com.mikepn.euclsystem.dtos.requests.meter.CreateMeterDTO;
import com.mikepn.euclsystem.dtos.response.meter.MeterResponseDTO;
import com.mikepn.euclsystem.exceptions.NotFoundException;
import com.mikepn.euclsystem.models.Customer;
import com.mikepn.euclsystem.models.Meter;
import com.mikepn.euclsystem.repositories.ICustomerRepository;
import com.mikepn.euclsystem.repositories.IMeterRepository;
import com.mikepn.euclsystem.services.IMeterService;
import com.mikepn.euclsystem.utils.helpers.TokenGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MeterServiceImpl implements IMeterService {

    private final IMeterRepository meterRepository;
    private final ICustomerRepository customerRepository;
    private final TokenGeneratorUtil tokenGeneratorUtil;

    @Override
    public MeterResponseDTO createMeter(CreateMeterDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        String generateMeter = tokenGeneratorUtil.generateUniqueMeterNumber();

        Meter meter = Meter.builder()
                .meterNumber(generateMeter)
                .customer(customer)
                .build();
        meterRepository.save(meter);
        return MeterResponseDTO.builder()
                .id(meter.getId())
                .meterNumber(meter.getMeterNumber())
                .customerFullName(meter.getCustomer().getProfile().getFirstName() + " " + meter.getCustomer().getProfile().getLastName())
                .build();
    }

    @Override
    public List<MeterResponseDTO> findMetersByCustomer(UUID customerId) {
        List<Meter> meters =  meterRepository.findMeterByCustomer_Id(customerId);
        return meters.stream()
                .map(meter -> MeterResponseDTO.builder()
                        .id(meter.getId())
                        .meterNumber(meter.getMeterNumber())
                        .customerFullName(meter.getCustomer().getProfile().getFirstName() + " " + meter.getCustomer().getProfile().getLastName())
                        .build())
                .toList();
    }
}
