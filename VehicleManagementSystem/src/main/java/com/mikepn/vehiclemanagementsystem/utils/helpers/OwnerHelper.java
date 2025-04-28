package com.mikepn.vehiclemanagementsystem.utils.helpers;


import com.mikepn.vehiclemanagementsystem.dtos.request.owner.CreateOwnerDTO;
import com.mikepn.vehiclemanagementsystem.models.Owner;
import com.mikepn.vehiclemanagementsystem.models.Role;
import com.mikepn.vehiclemanagementsystem.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OwnerHelper {

    public User buildUserFromDto(CreateOwnerDTO dto, Role role, PasswordEncoder passwordEncoder){
        return  User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .fullName(dto.getFirstName() + " " + dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .nationalId(dto.getNationalId())
                .phoneNumber(dto.getPhoneNumber())
                .roles(Set.of(role))
                .build();
    }


    public Owner buildOwner(User user, CreateOwnerDTO dto){
        return Owner.builder()
                .profile(user)
                .address(dto.getAddress())
                .build();
    }
}
