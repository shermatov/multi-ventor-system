package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

    Optional<Shop> findByUserId(Long userId);
}