package com.shermatov.ecommerce.repository;

import com.shermatov.ecommerce.domain.Order;
import com.shermatov.ecommerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    @Query("""
                SELECT DISTINCT o
                FROM Order o
                JOIN o.items i
                WHERE i.product.shop.id = :shopId
                """)
    List<Order> findOrdersByShopId(Long shopId);

}