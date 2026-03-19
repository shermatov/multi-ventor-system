package com.shermatov.ecommerce.dto.request;

import com.shermatov.ecommerce.security.PasswordConstraints;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ResetPasswordRequestDTO(

        @NotBlank
        String token,

        @NotBlank
        @Size(
                min = PasswordConstraints.MIN_LENGTH,
                max = PasswordConstraints.MAX_LENGTH,
                message = PasswordConstraints.ERROR_MESSAGE
        )
        @Pattern(
                regexp = PasswordConstraints.REGEX,
                message = PasswordConstraints.ERROR_MESSAGE
        )
        String newPassword,

        @NotBlank(message = PasswordConstraints.ERROR_MESSAGE)
        String confirmPassword
) {}