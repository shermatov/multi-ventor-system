package com.shermatov.ecommerce.dto.request;

import com.shermatov.ecommerce.security.PasswordConstraints;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {

    @Email(message = "Please provide a valid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(
            min = PasswordConstraints.MIN_LENGTH,
            max = PasswordConstraints.MAX_LENGTH,
            message = PasswordConstraints.ERROR_MESSAGE
    )
    @Pattern(
            regexp = PasswordConstraints.REGEX,
            message =  PasswordConstraints.ERROR_MESSAGE
    )
    private String password;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

}
