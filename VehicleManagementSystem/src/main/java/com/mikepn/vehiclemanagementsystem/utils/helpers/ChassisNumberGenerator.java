package com.mikepn.vehiclemanagementsystem.utils.helpers;

import com.mikepn.vehiclemanagementsystem.repositories.IVehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ChassisNumberGenerator {

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 12;
    private final IVehicleRepository vehicleRepository;

    private final Random random = new SecureRandom();

    public String generateChassisNumber() {
        StringBuilder sb = new StringBuilder("AN-"); // Optional prefix

        for (int i = 0; i < LENGTH; i++) {
            int index = random.nextInt(ALPHANUMERIC.length());
            sb.append(ALPHANUMERIC.charAt(index));
        }

        return sb.toString();
    }

    public String generateUniqueChasisNumber(){
        String chassisNumber;
        do{
            chassisNumber = generateChassisNumber();
        } while (vehicleRepository.existsByChassisNumber(chassisNumber));
        return chassisNumber;
    }
}
