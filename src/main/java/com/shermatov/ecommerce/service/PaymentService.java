package com.shermatov.ecommerce.service;

import com.shermatov.ecommerce.domain.Payment;
import com.shermatov.ecommerce.domain.User;

public interface PaymentService {

    Payment payOrder(Long orderId, User user);

}