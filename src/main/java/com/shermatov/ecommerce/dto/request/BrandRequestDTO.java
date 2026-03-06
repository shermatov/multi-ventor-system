package com.shermatov.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BrandRequestDTO {

    @NotBlank
    private String name;

    private String description;
}
