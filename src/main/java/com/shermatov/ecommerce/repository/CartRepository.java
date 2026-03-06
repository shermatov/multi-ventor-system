package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
