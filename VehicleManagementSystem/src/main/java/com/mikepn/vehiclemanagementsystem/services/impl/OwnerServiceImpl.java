package com.mikepn.vehiclemanagementsystem.services.impl;

import com.mikepn.vehiclemanagementsystem.dtos.request.owner.CreateOwnerDTO;
import com.mikepn.vehiclemanagementsystem.dtos.request.owner.UpdateOwnerDTO;
import com.mikepn.vehiclemanagementsystem.dtos.response.owner.OwnerResponseDTO;
import com.mikepn.vehiclemanagementsystem.enums.ERole;
import com.mikepn.vehiclemanagementsystem.exceptions.AppException;
import com.mikepn.vehiclemanagementsystem.exceptions.BadRequestException;
import com.mikepn.vehiclemanagementsystem.exceptions.NotFoundException;
import com.mikepn.vehiclemanagementsystem.mapper.OwnerMapper;
import com.mikepn.vehiclemanagementsystem.models.Owner;
import com.mikepn.vehiclemanagementsystem.models.PlateNumber;
import com.mikepn.vehiclemanagementsystem.models.Role;
import com.mikepn.vehiclemanagementsystem.models.User;
import com.mikepn.vehiclemanagementsystem.repositories.IOwnerRepository;
import com.mikepn.vehiclemanagementsystem.repositories.IUserRepository;
import com.mikepn.vehiclemanagementsystem.services.IOwnerService;
import com.mikepn.vehiclemanagementsystem.services.IRoleService;
import com.mikepn.vehiclemanagementsystem.utils.Mapper;
import com.mikepn.vehiclemanagementsystem.utils.helpers.OwnerHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements IOwnerService {

    private final IOwnerRepository ownerRepository;
    private final IUserRepository userRepository;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final OwnerHelper ownerHelper;


    @Override
    public OwnerResponseDTO createOwner(CreateOwnerDTO dto) {
        if(ownerRepository.existsByProfile_Email(dto.getEmail())){
            throw new BadRequestException("Owner already exists");
        }

        try{
            Role role = roleService.getRoleByName(ERole.STANDARD);

            User user = ownerHelper.buildUserFromDto(dto,role , passwordEncoder);
            user = userRepository.save(user);

            Owner owner = ownerHelper.buildOwner(user,dto);
            ownerRepository.save(owner);

            OwnerResponseDTO  response = OwnerMapper.mapToOwnerResponseDTO(user, owner);
            return  response;



        }catch (Exception e){
            throw new AppException("Failed to create Owner:  " + e.getMessage());
        }
    }

    @Override
    public Owner getOwnerById(UUID id) {
        return ownerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Owner not found with ID: " + id));
    }

    @Override
    public List<String> getPlateNumbersByOwnerId(UUID ownerId) {
        Owner owner = getOwnerById(ownerId);
        return owner.getPlateNumbers()
                .stream()
                .map(PlateNumber::getPlateNumber)
                .toList();
    }

    @Override
    public OwnerResponseDTO searchOwner(String keyword) {
        return ownerRepository.findOwnerByProfile_Email(keyword)
                .or(() -> ownerRepository.findOwnerByProfile_NationalId(keyword))
                .or(() -> ownerRepository.findOwnerByProfile_PhoneNumber(keyword))
                .map(owner -> OwnerMapper.mapToOwnerResponseDTO(owner.getProfile() , owner))
                .orElseThrow(() -> new NotFoundException("Owner not found with keyword: " + keyword));
    }


    @Override
    public Page<OwnerResponseDTO> getAllOwners(Pageable pageable) {
        Page<OwnerResponseDTO> owners = ownerRepository.findAll(pageable)
                .map(owner -> {
                    OwnerResponseDTO response = OwnerResponseDTO.builder()
                            .id(owner.getId())
                            .email(owner.getProfile().getEmail())
                            .Address(owner.getAddress())
                            .fullName(owner.getProfile().getFirstName() + " " + owner.getProfile().getLastName())
                            .phoneNumber(owner.getProfile().getPhoneNumber())
                            .nationalId(owner.getProfile().getNationalId())
                            .build();

                    return response;
                });

        System.out.println("Here are the owners: " + owners.getContent());
        return owners;
    }


    @Override
    public Owner updateOwner(UUID id, UpdateOwnerDTO dto) {
        Owner owner = getOwnerById(id);
        User profile = owner.getProfile();

        if (dto.getFirstName() != null) {
            profile.setFirstName(dto.getFirstName());
        }

        if (dto.getLastName() != null) {
            profile.setLastName(dto.getLastName());
        }

        if (dto.getFirstName() != null || dto.getLastName() != null) {
            String fullName = String.format("%s %s",
                    dto.getFirstName() != null ? dto.getFirstName() : profile.getFirstName(),
                    dto.getLastName() != null ? dto.getLastName() : profile.getLastName()
            );
            profile.setFullName(fullName.trim());
        }

        if (dto.getAddress() != null) {
            owner.setAddress(dto.getAddress());
        }

        userRepository.save(profile);
        return ownerRepository.save(owner);
    }


    @Override
    public void deleteOwner(UUID id) {
        Owner owner = getOwnerById(id);

        ownerRepository.delete(owner);
        userRepository.delete(owner.getProfile());
    }
}
