package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
