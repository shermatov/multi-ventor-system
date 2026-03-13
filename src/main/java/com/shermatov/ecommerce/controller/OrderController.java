package com.shermatov.ecommerce.controller;

import com.shermatov.ecommerce.domain.Order;
import com.shermatov.ecommerce.domain.OrderStatus;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.CreateOrderRequestDTO;
import com.shermatov.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(
            @RequestBody CreateOrderRequestDTO request,
            @AuthenticationPrincipal User user
    ) {
        return orderService.createOrder(request, user);
    }

    @GetMapping("/my")
    public List<Order> getMyOrders(
            @AuthenticationPrincipal User user
    ) {
        return orderService.getUserOrders(user);
    }

    @GetMapping("/{id}")
    public Order getOrderById(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        return orderService.getOrderById(id, user);
    }

    @PatchMapping("/{id}/cancel")
    public void cancelOrder(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        orderService.cancelOrder(id, user);
    }

    @PatchMapping("/{id}/status")
    public Order updateOrderStatus(
            @PathVariable Long id,
            @RequestParam OrderStatus status
    ) {
        return orderService.updateOrderStatus(id, status);
    }

    @GetMapping("/shop/{shopId}")
    public List<Order> getOrdersForShop(@PathVariable Long shopId) {
        return orderService.getOrdersForShop(shopId);
    }

    @PostMapping("/checkout")
    public Order checkout(
            @RequestParam Long addressId,
            @AuthenticationPrincipal User user
    ) {
        return orderService.checkout(user, addressId);
    }
}