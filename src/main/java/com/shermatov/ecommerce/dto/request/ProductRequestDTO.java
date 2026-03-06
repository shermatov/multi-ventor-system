package com.shermatov.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductRequestDTO {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long brandId;
    @NotNull
    private Long shopId;
    @NotNull
    private Integer stock; // initial stock
    private List<String> imageUrls;
}
