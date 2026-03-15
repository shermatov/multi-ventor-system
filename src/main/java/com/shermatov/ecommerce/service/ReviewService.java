package com.shermatov.ecommerce.service;

import com.shermatov.ecommerce.domain.Review;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.ReviewRequestDTO;

import java.util.List;

public interface ReviewService {

    Review createReview(ReviewRequestDTO request, User user);

    List<Review> getProductReviews(Long productId);

}