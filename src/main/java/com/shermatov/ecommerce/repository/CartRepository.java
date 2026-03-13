package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.Cart;
import com.shermatov.ecommerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUser(User user);

}