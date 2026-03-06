package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsByName(String name);
}
