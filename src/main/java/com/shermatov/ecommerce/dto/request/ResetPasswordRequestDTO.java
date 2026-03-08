package com.shermatov.ecommerce.dto.request;


import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequestDTO (@NotBlank String token,
                                    @NotBlank String newPassword)
{}
