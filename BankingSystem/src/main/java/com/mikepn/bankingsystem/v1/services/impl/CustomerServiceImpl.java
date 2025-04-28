package com.mikepn.bankingsystem.v1.services.impl;

import com.mikepn.bankingsystem.v1.dto.request.customer.CreateCustomerDTO;
import com.mikepn.bankingsystem.v1.dto.request.customer.UpdateCustomerDTO;
import com.mikepn.bankingsystem.v1.dto.response.customer.CustomerResponseDTO;
import com.mikepn.bankingsystem.v1.enums.ERole;
import com.mikepn.bankingsystem.v1.exceptions.AppException;
import com.mikepn.bankingsystem.v1.exceptions.BadRequestException;
import com.mikepn.bankingsystem.v1.exceptions.NotFoundException;
import com.mikepn.bankingsystem.v1.models.Account;
import com.mikepn.bankingsystem.v1.models.Customer;
import com.mikepn.bankingsystem.v1.models.Role;
import com.mikepn.bankingsystem.v1.models.User;
import com.mikepn.bankingsystem.v1.repositories.IAccountRepository;
import com.mikepn.bankingsystem.v1.repositories.ICustomerRepository;
import com.mikepn.bankingsystem.v1.repositories.IUserRepository;
import com.mikepn.bankingsystem.v1.services.ICustomerService;
import com.mikepn.bankingsystem.v1.services.IRoleService;
import com.mikepn.bankingsystem.v1.utils.Mapper;
import com.mikepn.bankingsystem.v1.utils.helpers.CustomerHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;
    private final IUserRepository userRepository;
    private final IAccountRepository accountRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerHelper customerHelper;

    @Override
    public CustomerResponseDTO createCustomer(CreateCustomerDTO dto) {
        if (customerRepository.existsByProfile_Email(dto.getEmail())) {
            throw new BadRequestException("The customer already exists");
        }

        try {
            Role role = roleService.getRoleByName(ERole.CUSTOMER);

            User user = customerHelper.buildUserFromDTO(dto, role, passwordEncoder);
            user = userRepository.save(user);

            Customer customer = customerHelper.buildCustomer(user);
            customer = customerRepository.save(customer);

            Account account = customerHelper.buildAccount(customer);
            account = accountRepository.save(account);

            customer.setAccount(account);
            customer = customerRepository.save(customer);

            CustomerResponseDTO response = Mapper.getMapper().map(user, CustomerResponseDTO.class);
            response.setId(customer.getId());
            response.setAccountNumber(account.getAccountNumber());
            response.setBalance(account.getBalance());

            return response;
        } catch (Exception e) {
            throw new AppException("Failed to create customer: " + e.getMessage());
        }
    }


    @Override
    public Customer getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found with ID: " + customerId));
    }

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }


    @Override
    public Customer updateCustomer(UUID customerId, UpdateCustomerDTO dto) {
        Customer customer = getCustomerById(customerId);
        User profile = customer.getProfile();

        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());
        profile.setFullName(dto.getFirstName() + " " + dto.getLastName());
        profile.setDob(dto.getDateOfBirth());

        userRepository.save(profile);
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        Customer customer = getCustomerById(customerId);

        accountRepository.delete(customer.getAccount());
        userRepository.delete(customer.getProfile());
        customerRepository.delete(customer);
    }
}
