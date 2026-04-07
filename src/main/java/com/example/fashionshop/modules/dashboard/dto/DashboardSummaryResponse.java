package com.example.fashionshop.modules.dashboard.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@Builder
public class DashboardSummaryResponse {
    private long totalUsers;
    private long totalCustomers;
    private long totalStaff;
    private long totalProducts;
    private long totalOrders;
    private BigDecimal totalRevenue;
    private Map<String, Long> orderCountByStatus;
}
