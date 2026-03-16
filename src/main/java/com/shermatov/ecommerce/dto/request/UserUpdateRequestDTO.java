package com.shermatov.ecommerce.dto.request;
import com.shermatov.ecommerce.security.PasswordConstraints;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDTO {

    @NotBlank(message = "Name can not be null or blank.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String firstName;

    @NotBlank(message = "Email can not be null or blank.")
    @Email(message = "Pls provide a valid email.")
    private String email;

    @NotBlank(message = "Name can not be null or blank.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String lastName;

    private String role;
}