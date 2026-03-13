package com.shermatov.ecommerce.dto.response;

import com.shermatov.ecommerce.domain.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponseDTO {

    private Long id;

    private BigDecimal totalPrice;

    private OrderStatus status;

    private LocalDateTime orderDate;

}