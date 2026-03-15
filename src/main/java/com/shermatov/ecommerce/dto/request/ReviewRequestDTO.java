package com.shermatov.ecommerce.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDTO {

    private Long productId;

    private Integer rating;

    private String comment;

}