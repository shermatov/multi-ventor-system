package com.shermatov.ecommerce.dto.request;

import com.shermatov.ecommerce.security.PasswordConstraints;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateDTO {

    @Nullable
    @Size(
            min = PasswordConstraints.MIN_LENGTH,
            max = PasswordConstraints.MAX_LENGTH,
            message = PasswordConstraints.ERROR_MESSAGE
    )
    @Pattern(
            regexp = PasswordConstraints.REGEX,
            message = PasswordConstraints.ERROR_MESSAGE
    )
    private String password;

    @NotBlank(message = "First name cannot be blank.")
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank(message = "Last name cannot be blank.")
    @Size(min = 2, max = 50)
    private String lastName;
}