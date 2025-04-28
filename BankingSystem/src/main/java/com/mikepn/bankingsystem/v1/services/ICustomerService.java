package com.mikepn.bankingsystem.v1.services;

import com.mikepn.bankingsystem.v1.dto.request.customer.CreateCustomerDTO;
import com.mikepn.bankingsystem.v1.dto.request.customer.UpdateCustomerDTO;
import com.mikepn.bankingsystem.v1.dto.response.customer.CustomerResponseDTO;
import com.mikepn.bankingsystem.v1.models.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ICustomerService {

    CustomerResponseDTO createCustomer(CreateCustomerDTO dto);

    Customer getCustomerById(UUID customerId);

    Page<Customer> getAllCustomers(Pageable pageable);

    Customer updateCustomer(UUID customerId, UpdateCustomerDTO dto);

    void deleteCustomer(UUID customerId);
}