package com.shermatov.ecommerce.controller;

import com.shermatov.ecommerce.domain.Payment;
import com.shermatov.ecommerce.domain.User;
import com.shermatov.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/order/{orderId}")
    public Payment payOrder(
            @PathVariable Long orderId,
            @AuthenticationPrincipal User user
    ) {
        return paymentService.payOrder(orderId, user);
    }
}