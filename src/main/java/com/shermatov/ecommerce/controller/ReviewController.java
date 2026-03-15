package com.shermatov.ecommerce.controller;

import com.shermatov.ecommerce.domain.Review;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.ReviewRequestDTO;
import com.shermatov.ecommerce.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Review createReview(
            @Valid @RequestBody ReviewRequestDTO request,
            @AuthenticationPrincipal User user
    ) {
        return reviewService.createReview(request, user);
    }

    @GetMapping("/product/{productId}")
    public List<Review> getProductReviews(
            @PathVariable Long productId
    ) {
        return reviewService.getProductReviews(productId);
    }


}