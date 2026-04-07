package com.example.fashionshop.modules.dashboard.controller;

import com.example.fashionshop.common.response.ApiResponse;
import com.example.fashionshop.modules.dashboard.dto.DashboardSummaryResponse;
import com.example.fashionshop.modules.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ApiResponse<DashboardSummaryResponse> summary() {
        return ApiResponse.success("Dashboard fetched successfully", dashboardService.getSummary());
    }
}
