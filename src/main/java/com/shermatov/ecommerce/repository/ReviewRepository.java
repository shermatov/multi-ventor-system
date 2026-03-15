package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.Product;
import com.shermatov.ecommerce.domain.Review;
import com.shermatov.ecommerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProduct(Product product);

    Optional<Review> findByUserAndProduct(User user, Product product);

    @Query("""
                SELECT AVG(r.rating)
                FROM Review r
                WHERE r.product.id = :productId
                """)
    Double getAverageRating(Long productId);

    @Query("""
                SELECT COUNT(r)
                FROM Review r
                WHERE r.product.id = :productId
                """)
    Integer getReviewCount(Long productId);

}