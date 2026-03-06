package com.shermatov.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShopResponseDTO {

    private Long id;

    private String name;

    private String description;

    private Long ownerId;
}