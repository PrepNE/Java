package com.mikepn.vehiclemanagementsystem.dtos.response.owner;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OwnerResponseDTO {
    private UUID id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String Address;
    private String nationalId;
}