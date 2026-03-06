package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
