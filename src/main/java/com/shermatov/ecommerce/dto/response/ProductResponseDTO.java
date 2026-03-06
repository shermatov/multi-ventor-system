package com.shermatov.ecommerce.dto.response;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;

    @NotNull
    private BigDecimal price;
    private Long categoryId;
    private Long brandId;
    private Long shopId;

    @NotNull
    private Integer stock;
    private List<String> imageUrls;
}
