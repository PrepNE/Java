package com.mikepn.vehiclemanagementsystem.dtos.request.user;


import com.mikepn.vehiclemanagementsystem.dtos.request.auth.RegisterUserDTO;
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
        this.setNationalId(registerUserDTO.getNationalId());
        this.adminCreateCode = adminCreateCode;
    }

    public CreateAdminDTO(RegisterUserDTO registerUserDTO ){
        this.setFirstName(registerUserDTO.getFirstName());
        this.setLastName(registerUserDTO.getLastName());
        this.setEmail(registerUserDTO.getEmail());
        this.setPhoneNumber(registerUserDTO.getPhoneNumber());
        this.setPassword(registerUserDTO.getPassword());
        this.setNationalId(registerUserDTO.getNationalId());
    }

}