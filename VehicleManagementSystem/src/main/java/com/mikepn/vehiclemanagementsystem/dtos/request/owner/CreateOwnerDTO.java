package com.mikepn.vehiclemanagementsystem.dtos.request.owner;


import com.mikepn.vehiclemanagementsystem.dtos.request.auth.RegisterUserDTO;
import lombok.Data;

@Data
public class CreateOwnerDTO extends RegisterUserDTO {
    private String address;
}
