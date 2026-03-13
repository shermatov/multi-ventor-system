package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.*;
import com.shermatov.ecommerce.dto.request.AddToCartRequestDTO;
import com.shermatov.ecommerce.exception.ResourceNotFoundException;
import com.shermatov.ecommerce.repository.CartRepository;
import com.shermatov.ecommerce.repository.ProductRepository;
import com.shermatov.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public Cart getCart(User user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> createCart(user));
    }

    @Override
    @Transactional
    public Cart addToCart(AddToCartRequestDTO request, User user) {

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> createCart(user));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Optional<CartItem> existingItem = cart.getItems()
                .stream()
                .filter(i -> i.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItem.isPresent()) {

            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());

        } else {

            CartItem item = new CartItem();
            item.setCart(cart);
            item.setProduct(product);
            item.setQuantity(request.getQuantity());

            cart.getItems().add(item);
        }

        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void removeItem(Long cartItemId, User user) {

        Cart cart = getCart(user);

        cart.getItems().removeIf(item -> item.getId().equals(cartItemId));

        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void clearCart(User user) {

        Cart cart = getCart(user);

        cart.getItems().clear();

        cartRepository.save(cart);
    }

    private Cart createCart(User user) {

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setItems(new ArrayList<>());

        return cartRepository.save(cart);
    }
}