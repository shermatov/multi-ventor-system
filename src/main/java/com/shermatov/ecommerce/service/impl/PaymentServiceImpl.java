package com.shermatov.ecommerce.service.impl;

import com.shermatov.ecommerce.domain.*;
import com.shermatov.ecommerce.repository.OrderRepository;
import com.shermatov.ecommerce.repository.PaymentRepository;
import com.shermatov.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public Payment payOrder(Long orderId, User user) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // Only owner can pay
        if (!order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Cannot pay for this order");
        }

        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Order is already paid or processed");
        }

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice());
        payment.setPaymentMethod(PaymentMethod.MOCK); // or user selection
        payment.setStatus(OrderStatus.PAID);
        payment.setName("Payment for order #" + order.getId());
        payment.setDescription("Payment processed at " + LocalDateTime.now());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setUpdatedAt(LocalDateTime.now());

        // Mark order as PAID
        order.setStatus(OrderStatus.PAID);

        paymentRepository.save(payment);
        orderRepository.save(order);

        return payment;
    }
}