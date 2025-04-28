package com.mikepn.euclsystem.utils.helpers;


import com.mikepn.euclsystem.dtos.requests.customer.CreateCustomerDTO;
import com.mikepn.euclsystem.models.Customer;
import com.mikepn.euclsystem.models.Role;
import com.mikepn.euclsystem.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomerHelper {


    public User buildUserFromDto(CreateCustomerDTO dto , Role role , PasswordEncoder passwordEncoder){
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .fullName(dto.getFirstName() + " " + dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .roles(Set.of(role))
                .build();
    }



    public Customer buildCustomer(User profile){
        return Customer.builder()
                .profile(profile)
                .build();
    }

}
