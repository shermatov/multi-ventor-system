package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
