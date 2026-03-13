package com.shermatov.ecommerce.dto.request;

import lombok.Data;

@Data
public class OrderItemRequestDTO {

    private Long productId;

    private Integer quantity;

}