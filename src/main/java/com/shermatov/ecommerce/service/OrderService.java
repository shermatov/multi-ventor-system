package com.shermatov.ecommerce.service;

import com.shermatov.ecommerce.domain.Order;
import com.shermatov.ecommerce.domain.OrderStatus;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.dto.request.CreateOrderRequestDTO;

import java.util.List;

public interface OrderService {

    Order createOrder(CreateOrderRequestDTO request, User user);

    List<Order> getUserOrders(User user);

    Order getOrderById(Long orderId, User user);

    void cancelOrder(Long orderId, User user);

    Order updateOrderStatus(Long orderId, OrderStatus status);

    List<Order> getOrdersForShop(Long shopId);

    Order checkout(User user, Long addressId);

}