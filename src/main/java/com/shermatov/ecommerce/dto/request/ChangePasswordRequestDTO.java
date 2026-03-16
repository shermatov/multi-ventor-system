package com.shermatov.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequestDTO(

        @NotBlank
        String currentPassword,

        @NotBlank
        String newPassword,

        @NotBlank
        String confirmPassword
) {}