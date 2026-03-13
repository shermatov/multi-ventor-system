package com.shermatov.ecommerce.service;

import com.shermatov.ecommerce.domain.Cart;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.AddToCartRequestDTO;

public interface CartService {

    Cart getCart(User user);

    Cart addToCart(AddToCartRequestDTO request, User user);

    void removeItem(Long cartItemId, User user);

    void clearCart(User user);

}