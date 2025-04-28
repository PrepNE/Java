package com.mikepn.bankingsystem.v1.dto.request.user;

import com.mikepn.bankingsystem.v1.dto.request.auth.RegisterUserDTO;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Getter
@Setter
public class CreateAdminDTO extends RegisterUserDTO {
    private String adminCreateCode;
    public CreateAdminDTO(RegisterUserDTO registerUserDTO , String adminCreateCode){
        this.setFirstName(registerUserDTO.getFirstName());
        this.setLastName(registerUserDTO.getLastName());
        this.setEmail(registerUserDTO.getEmail());
        this.setPhoneNumber(registerUserDTO.getPhoneNumber());
        this.setPassword(registerUserDTO.getPassword());
        this.setDateOfBirth(registerUserDTO.getDateOfBirth());
        this.adminCreateCode = adminCreateCode;
    }

    public CreateAdminDTO(RegisterUserDTO registerUserDTO ){
        this.setFirstName(registerUserDTO.getFirstName());
        this.setLastName(registerUserDTO.getLastName());
        this.setEmail(registerUserDTO.getEmail());
        this.setPhoneNumber(registerUserDTO.getPhoneNumber());
        this.setPassword(registerUserDTO.getPassword());
        this.setDateOfBirth(registerUserDTO.getDateOfBirth());
    }

}