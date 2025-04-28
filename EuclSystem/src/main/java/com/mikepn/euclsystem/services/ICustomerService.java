package com.mikepn.euclsystem.services;

import com.mikepn.euclsystem.dtos.requests.customer.CreateCustomerDTO;
import com.mikepn.euclsystem.dtos.requests.customer.UpdateCustomerDTO;
import com.mikepn.euclsystem.dtos.response.customer.CustomerResponseDTO;
import com.mikepn.euclsystem.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ICustomerService {

    CustomerResponseDTO createCustomer(CreateCustomerDTO dto);

    Customer getCustomerById(UUID customerId);

    Page<CustomerResponseDTO> getAllCustomers(Pageable pageable);

    Customer updateCustomer(UUID customerId, UpdateCustomerDTO dto);

    void deleteCustomer(UUID customerId);
}