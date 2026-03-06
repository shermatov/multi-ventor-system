package com.shermatov.ecommerce.domain;

public enum OrderStatus {
    PENDING,     // Order created but not paid
    PAID,        // Payment completed
    PROCESSING,  // Shop is preparing the order
    SHIPPED,     // Order shipped to customer
    DELIVERED,   // Customer received the order
    CANCELLED    // Order cancelled
}
