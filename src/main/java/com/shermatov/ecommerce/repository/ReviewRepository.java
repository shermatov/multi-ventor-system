package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
