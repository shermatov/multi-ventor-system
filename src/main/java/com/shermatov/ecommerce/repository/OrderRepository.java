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

    @Query("""
        SELECT JPQLCOUNT(oi) > 0
        FROM OrderItem oi
        JOIN oi.order o
        WHERE o.user.id = :userId
        AND oi.product.id = :productId
        AND o.status IN ('PAID','PROCESSING','SHIPPED','DELIVERED')
        """)
    boolean hasUserPurchasedProduct(Long userId, Long productId);

}