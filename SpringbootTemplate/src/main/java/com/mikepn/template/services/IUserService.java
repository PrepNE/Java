package com.mikepn.template.services;

import com.mikepn.template.dtos.request.auth.UpdateUserDTO;
import com.mikepn.template.dtos.request.user.CreateAdminDTO;
import com.mikepn.template.dtos.request.user.UserResponseDTO;
import com.mikepn.template.dtos.request.user.UserRoleModificationDTO;
import com.mikepn.template.models.User;

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
