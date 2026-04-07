package com.example.fashionshop.modules.order.controller;

import com.example.fashionshop.common.response.ApiResponse;
import com.example.fashionshop.modules.order.dto.CancelOrderRequest;
import com.example.fashionshop.modules.order.dto.OrderResponse;
import com.example.fashionshop.modules.order.dto.OrderStatusResponse;
import com.example.fashionshop.modules.order.dto.PlaceOrderRequest;
import com.example.fashionshop.modules.order.dto.UpdateOrderStatusRequest;
import com.example.fashionshop.modules.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<OrderResponse> placeOrder(@Valid @RequestBody PlaceOrderRequest request) {
        return ApiResponse.success("Order placed successfully", orderService.placeOrder(request));
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<List<OrderResponse>> myOrders() {
        return ApiResponse.success("Orders fetched successfully", orderService.getMyOrders());
    }

    @GetMapping("/my/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<OrderResponse> myOrderDetail(@PathVariable Integer orderId) {
        return ApiResponse.success("Order detail fetched successfully", orderService.getMyOrderDetail(orderId));
    }

    @GetMapping("/my/{orderId}/status")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<OrderStatusResponse> trackOrderStatus(@PathVariable Integer orderId) {
        return ApiResponse.success("Order status fetched successfully", orderService.trackOrderStatus(orderId));
    }

    @PatchMapping("/my/{orderId}/cancel")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ApiResponse<Void> cancelMyOrder(@PathVariable Integer orderId) {
        orderService.cancelMyOrder(orderId);
        return ApiResponse.success("Order cancelled successfully", null);
    }

    @GetMapping("/manage")
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    public ApiResponse<List<OrderResponse>> allOrders() {
        return ApiResponse.success("Orders fetched successfully", orderService.getAllOrders());
    }

    @GetMapping("/manage/{orderId}")
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    public ApiResponse<OrderResponse> orderDetail(@PathVariable Integer orderId) {
        return ApiResponse.success("Order detail fetched successfully", orderService.getOrderDetail(orderId));
    }

    @PatchMapping("/manage/{orderId}/status")
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    public ApiResponse<OrderResponse> updateStatus(@PathVariable Integer orderId,
            @Valid @RequestBody UpdateOrderStatusRequest request) {
        return ApiResponse.success("Order status updated successfully",
                orderService.updateOrderStatus(orderId, request));
    }

    @PatchMapping("/manage/{orderId}/cancel-with-reason")
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    public ApiResponse<Void> cancelOrder(@PathVariable Integer orderId,
            @Valid @RequestBody CancelOrderRequest request) {
        orderService.cancelOrder(orderId, request);
        return ApiResponse.success("Order cancelled successfully", null);
    }

    @PatchMapping("/manage/{orderId}/cancel")
    @PreAuthorize("hasAnyRole('STAFF','ADMIN')")
    public ApiResponse<Void> cancelOrderByStaff(@PathVariable Integer orderId) {
        orderService.cancelOrderByStaff(orderId);
        return ApiResponse.success("Order cancelled successfully", null);
    }
}
