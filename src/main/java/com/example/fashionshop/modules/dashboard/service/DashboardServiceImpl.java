package com.example.fashionshop.modules.dashboard.service;

import com.example.fashionshop.common.enums.OrderStatus;
import com.example.fashionshop.common.enums.Role;
import com.example.fashionshop.modules.dashboard.dto.DashboardSummaryResponse;
import com.example.fashionshop.modules.order.entity.Order;
import com.example.fashionshop.modules.order.repository.OrderRepository;
import com.example.fashionshop.modules.product.repository.ProductRepository;
import com.example.fashionshop.modules.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public DashboardSummaryResponse getSummary() {
        Map<String, Long> orderCountByStatus = Arrays.stream(OrderStatus.values())
                .collect(Collectors.toMap(Enum::name, orderRepository::countByStatus));

        BigDecimal revenue = orderRepository.findByStatus(OrderStatus.COMPLETED).stream()
                .map(Order::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return DashboardSummaryResponse.builder()
                .totalUsers(userRepository.count())
                .totalCustomers(userRepository.countByRole(Role.CUSTOMER))
                .totalStaff(userRepository.countByRole(Role.STAFF))
                .totalProducts(productRepository.count())
                .totalOrders(orderRepository.count())
                .totalRevenue(revenue)
                .orderCountByStatus(orderCountByStatus)
                .build();
    }
}
