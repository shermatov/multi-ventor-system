package com.shermatov.ecommerce.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDTO(LocalDateTime timestamp, String error, int status) {
}