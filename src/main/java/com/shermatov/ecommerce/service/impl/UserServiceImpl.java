package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.Role;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.UserUpdateRequestDTO;
import com.shermatov.ecommerce.dto.response.UserResponseDTO;
import com.shermatov.ecommerce.exception.ResourceNotFoundException;
import com.shermatov.ecommerce.repository.UserRepository;
import com.shermatov.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponseDTO> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return toResponse(user);
    }

    @Override
    public UserResponseDTO update(Long id, UserUpdateRequestDTO request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setName(request.getFirstName() + " " + request.getLastName());

        // update password if provided
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        // update role if provided
        if (request.getRole() != null) {
            user.setRole(Role.valueOf(request.getRole()));
        }

        User saved = userRepository.save(user);

        return toResponse(saved);
    }

    @Override
    public void delete(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        userRepository.delete(user);
    }

    @Override
    public UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole()
        );
    }
}