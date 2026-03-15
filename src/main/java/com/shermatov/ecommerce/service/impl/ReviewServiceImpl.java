package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.Product;
import com.shermatov.ecommerce.domain.Review;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.ReviewRequestDTO;
import com.shermatov.ecommerce.repository.OrderRepository;
import com.shermatov.ecommerce.repository.ProductRepository;
import com.shermatov.ecommerce.repository.ReviewRepository;
import com.shermatov.ecommerce.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public Review createReview(ReviewRequestDTO request, User user) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        boolean purchased = orderRepository.hasUserPurchasedProduct(
                user.getId(),
                product.getId()
        );

        if (!purchased) {
            throw new RuntimeException("You can only review products you purchased");
        }

        reviewRepository.findByUserAndProduct(user, product)
                .ifPresent(r -> {
                    throw new RuntimeException("You already reviewed this product");
                });

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setName("Review for " + product.getName());

        Review savedReview = reviewRepository.save(review);

        // ⭐ Recalculate product rating
        Double avgRating = reviewRepository.getAverageRating(product.getId());
        Integer reviewCount = reviewRepository.getReviewCount(product.getId());

        product.setAverageRating(avgRating != null ? avgRating : 0.0);
        product.setReviewCount(reviewCount);

        productRepository.save(product);

        return savedReview;
    }

    @Override
    public List<Review> getProductReviews(Long productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return reviewRepository.findByProduct(product);
    }
}