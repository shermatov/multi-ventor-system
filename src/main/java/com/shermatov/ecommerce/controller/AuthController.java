package com.shermatov.ecommerce.controller;

import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.*;
import com.shermatov.ecommerce.dto.response.LoginResponseDTO;
import com.shermatov.ecommerce.dto.response.MessageResponseDTO;
import com.shermatov.ecommerce.dto.response.UserResponseDTO;
import com.shermatov.ecommerce.service.AuthService;
import com.shermatov.ecommerce.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(
            @Valid @RequestBody UserCreateRequestDTO request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<MessageResponseDTO> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequestDTO request) {

        passwordResetService.forgotPassword(request.email());

        return ResponseEntity.ok(
                new MessageResponseDTO("Password reset link will be sent in a few minutes."));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponseDTO> resetPassword(
            @Valid @RequestBody ResetPasswordRequestDTO request) {

        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Passwords do not match"
            );
        }

        passwordResetService.resetPassword(
                request.token(),
                request.newPassword()
        );

        return ResponseEntity.ok(
                new MessageResponseDTO("Password has been reset successfully")
        );
    }

    @PutMapping("/change-password")
    public ResponseEntity<MessageResponseDTO> changePassword(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody ChangePasswordRequestDTO request) {

        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Passwords do not match"
            );
        }

        passwordResetService.changePassword(
                user,
                request.currentPassword(),
                request.newPassword()
        );

        return ResponseEntity.ok(
                new MessageResponseDTO("Password changed successfully")
        );
    }
}