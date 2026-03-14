package com.shermatov.ecommerce.service;

import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.UserProfileUpdateDTO;
import com.shermatov.ecommerce.dto.request.UserUpdateRequestDTO;
import com.shermatov.ecommerce.dto.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserResponseDTO> getUsers(Pageable pageable);

    UserResponseDTO getUserById(Long id);

    UserResponseDTO update(Long id, UserUpdateRequestDTO request);

    void delete(Long id);

    UserResponseDTO toResponse(User user);

    User updateProfile(Long id, UserProfileUpdateDTO dto);

}