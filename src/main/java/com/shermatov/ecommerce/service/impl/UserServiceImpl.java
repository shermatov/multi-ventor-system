package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.Role;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.UserProfileUpdateDTO;
import com.shermatov.ecommerce.dto.request.UserUpdateRequestDTO;
import com.shermatov.ecommerce.dto.response.UserResponseDTO;
import com.shermatov.ecommerce.exception.ResourceNotFoundException;
import com.shermatov.ecommerce.repository.UserRepository;
import com.shermatov.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    @Transactional
    public UserResponseDTO updateProfile(Long id, UserProfileUpdateDTO request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean changed = applyBasicUpdates(
                user,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail()
        );

        if (!changed) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No changes detected"
            );
        }

        userRepository.save(user);

        return toResponse(user);
    }

    @Override
    @Transactional
    public UserResponseDTO update(Long id, UserUpdateRequestDTO request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        boolean changed = applyBasicUpdates(
                user,
                request.getFirstName(),
                request.getLastName(),
                request.getEmail()
        );

        if (request.getRole() != null) {
            try {
                Role newRole = Role.valueOf(request.getRole());

                if (!newRole.equals(user.getRole())) {
                    user.setRole(newRole);
                    changed = true;
                }

            } catch (IllegalArgumentException ex) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Invalid role: " + request.getRole(),
                        ex
                );
            }
        }

        if (!changed) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No changes detected"
            );
        }

        userRepository.save(user);

        return toResponse(user);
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

    private boolean applyBasicUpdates(User user, String firstName, String lastName, String email) {

        boolean changed = false;

        if (firstName != null && !firstName.equals(user.getFirstName())) {
            user.setFirstName(firstName);
            changed = true;
        }

        if (lastName != null && !lastName.equals(user.getLastName())) {
            user.setLastName(lastName);
            changed = true;
        }

        if (firstName != null && lastName != null) {
            user.setName(firstName + " " + lastName);
        }

        if (email != null && !email.equals(user.getEmail())) {
            user.setEmail(email);
            changed = true;
        }

        return changed;
    }


}