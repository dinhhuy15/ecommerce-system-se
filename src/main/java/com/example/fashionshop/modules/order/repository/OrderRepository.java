package com.example.fashionshop.modules.order.repository;

import com.example.fashionshop.common.enums.OrderStatus;
import com.example.fashionshop.modules.order.entity.Order;
import com.example.fashionshop.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByUser(User user);

    long countByStatus(OrderStatus status);

    List<Order> findByStatus(OrderStatus status);
}
