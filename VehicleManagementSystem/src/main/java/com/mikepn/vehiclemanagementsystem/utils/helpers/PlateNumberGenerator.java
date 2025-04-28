package com.mikepn.vehiclemanagementsystem.utils.helpers;


import com.mikepn.vehiclemanagementsystem.models.PlateNumber;
import com.mikepn.vehiclemanagementsystem.repositories.IPlateNumberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PlateNumberGenerator {

    private static final int MAX_NUMBER =999;
    private static final char MIN_SUFFIX='A';
    private static final char MAX_SUFFIX='Z';
    private static final String PREFIX_START="RAA";
    private static final String PREFIX_END="RZZ";


    private final IPlateNumberRepository plateNumberRepository;

    public String generateNextPlateNumber() {
        Optional<PlateNumber> lastIssued = plateNumberRepository.findTopByOrderByIssuedDateDesc();

        String nextPlate;
        if (lastIssued.isEmpty()) {
            nextPlate = formatPlate(PREFIX_START, 1, MIN_SUFFIX);
        } else {
            String[] parts = lastIssued.get().getPlateNumber().split(" "); // e.g. [RAA, 001, A]
            String prefix = parts[0];
            int number = Integer.parseInt(parts[1]);
            char suffix = parts[2].charAt(0);

            if (number < MAX_NUMBER) {
                number++;
            } else {
                number = 1;
                if (suffix < MAX_SUFFIX) {
                    suffix++;
                } else {
                    suffix = MIN_SUFFIX;
                    prefix = incrementPrefix(prefix);
                }
            }

            nextPlate = formatPlate(prefix, number, suffix);
        }

        return nextPlate;
    }



    private String formatPlate(String prefix, int number , char suffix){
        return String.format("%s %03d %c", prefix, number, suffix);
    }

    private String incrementPrefix(String prefix){
        char[] chars = prefix.toCharArray();

        for(int i=2; i>=0; i--){
            if(chars[i] < 'Z'){
                chars[i]++;
                break;
            }else {
                chars[i] = 'A';
            }
        }

        String newPrefix = new String(chars);

        if(newPrefix.compareTo(PREFIX_END) > 0){
            throw new RuntimeException("Plate number prefix reached maximum value");
        }
        return newPrefix;
    }


}
