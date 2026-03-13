package com.shermatov.ecommerce.dto.request;

import lombok.Data;

import java.util.List;


@Data
public class CreateOrderRequestDTO {

    private Long addressId;

    private List<OrderItemRequestDTO> items;

}