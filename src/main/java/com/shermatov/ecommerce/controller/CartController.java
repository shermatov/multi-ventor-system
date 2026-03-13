package com.shermatov.ecommerce.controller;

import com.shermatov.ecommerce.domain.Cart;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.AddToCartRequestDTO;
import com.shermatov.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Cart getCart(@AuthenticationPrincipal User user) {
        return cartService.getCart(user);
    }

    @PostMapping
    public Cart addToCart(
            @RequestBody AddToCartRequestDTO request,
            @AuthenticationPrincipal User user
    ) {
        return cartService.addToCart(request, user);
    }

    @DeleteMapping("/item/{id}")
    public void removeItem(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        cartService.removeItem(id, user);
    }

    @DeleteMapping("/clear")
    public void clearCart(@AuthenticationPrincipal User user) {
        cartService.clearCart(user);
    }
}