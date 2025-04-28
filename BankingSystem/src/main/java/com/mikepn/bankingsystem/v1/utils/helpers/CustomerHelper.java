package com.mikepn.bankingsystem.v1.utils.helpers;

import com.mikepn.bankingsystem.v1.dto.request.customer.CreateCustomerDTO;
import com.mikepn.bankingsystem.v1.models.Account;
import com.mikepn.bankingsystem.v1.models.Customer;
import com.mikepn.bankingsystem.v1.models.Role;
import com.mikepn.bankingsystem.v1.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;

@Component
public class CustomerHelper {

    public User buildUserFromDTO(CreateCustomerDTO dto, Role role, PasswordEncoder encoder) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .fullName(dto.getFirstName() + " " + dto.getLastName())
                .email(dto.getEmail())
                .dob(dto.getDateOfBirth())
                .password(encoder.encode(dto.getPassword()))
                .roles(Set.of(role))
                .build();
    }

    public Customer buildCustomer(User user) {
        return Customer.builder()
                .profile(user)
                .build();
    }

    public Account buildAccount(Customer customer) {
        return Account.builder()
                .owner(customer)
                .accountNumber(generateAccountNumber())
                .build();
    }

    public String generateAccountNumber() {
        String prefix = "21"; // Bank identifier
        long number = 100000000L + new Random().nextLong(900000000L);
        return prefix + Math.abs(number); // Example: 2112345678
    }
}
