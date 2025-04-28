package com.mikepn.vehiclemanagementsystem.mapper;

import com.mikepn.vehiclemanagementsystem.dtos.response.owner.OwnerResponseDTO;
import com.mikepn.vehiclemanagementsystem.models.Owner;
import com.mikepn.vehiclemanagementsystem.models.User;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {


    public static OwnerResponseDTO mapToOwnerResponseDTO(User user , Owner owner){

        return OwnerResponseDTO.builder()
                .id(owner.getId())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .nationalId(user.getNationalId())
                .Address(owner.getAddress())
                .build();
    }


}
