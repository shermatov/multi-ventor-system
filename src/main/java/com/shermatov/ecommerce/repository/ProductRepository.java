package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductImage, Long> {
}
