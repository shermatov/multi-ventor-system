package com.shermatov.ecommerce.controller;

import com.shermatov.ecommerce.dto.request.UserProfileUpdateDTO;
import com.shermatov.ecommerce.dto.request.UserUpdateRequestDTO;
import com.shermatov.ecommerce.dto.response.UserResponseDTO;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // ADMIN: get all users
    @GetMapping
    public Page<UserResponseDTO> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    // ADMIN: get user by id
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // CURRENT USER PROFILE
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.toResponse(user));
    }

    // UPDATE CURRENT USER PROFILE
    @PutMapping("/me")
    public ResponseEntity<UserResponseDTO> updateProfile(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid UserProfileUpdateDTO request) {

        UserResponseDTO updated = userService.updateProfile(user.getId(), request);
        return ResponseEntity.ok(updated);
    }

    // ADMIN: update user by id
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody @Valid UserUpdateRequestDTO request) {

        return ResponseEntity.ok(userService.update(id, request));
    }

    // ADMIN: delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}