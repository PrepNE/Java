package com.mikepn.euclsystem.services;

import com.mikepn.euclsystem.dtos.requests.auth.UpdateUserDTO;
import com.mikepn.euclsystem.dtos.requests.user.CreateAdminDTO;
import com.mikepn.euclsystem.dtos.requests.user.UserResponseDTO;
import com.mikepn.euclsystem.dtos.requests.user.UserRoleModificationDTO;
import com.mikepn.euclsystem.models.User;

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
