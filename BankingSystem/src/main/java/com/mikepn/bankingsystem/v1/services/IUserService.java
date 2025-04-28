package com.mikepn.bankingsystem.v1.services;

import com.mikepn.bankingsystem.v1.dto.request.auth.UpdateUserDTO;
import com.mikepn.bankingsystem.v1.dto.request.user.CreateAdminDTO;
import com.mikepn.bankingsystem.v1.dto.request.user.UserResponseDTO;
import com.mikepn.bankingsystem.v1.dto.request.user.UserRoleModificationDTO;
import com.mikepn.bankingsystem.v1.models.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {

    User findUserById(UUID userId);

    User getLoggedInUser();

    UserResponseDTO createAdmin(CreateAdminDTO createUserDTO);

    List<User> getUsers();

    UserResponseDTO getUserById(UUID uuid);

    UserResponseDTO updateUser(UUID userId, UpdateUserDTO updateUserDTO);

    UserResponseDTO addRoles(UUID userId, UserRoleModificationDTO userRoleModificationDTO);

    UserResponseDTO removeRoles(UUID userId, UserRoleModificationDTO userRoleModificationDTO);

    void deleteUser(UUID userId);
}
