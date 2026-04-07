package com.example.fashionshop.modules.order.service;

import com.example.fashionshop.modules.order.dto.CancelOrderRequest;
import com.example.fashionshop.modules.order.dto.OrderResponse;
import com.example.fashionshop.modules.order.dto.PlaceOrderRequest;
import com.example.fashionshop.modules.order.dto.UpdateOrderStatusRequest;
import com.example.fashionshop.modules.order.dto.OrderStatusResponse;

import java.util.List;

public interface OrderService {
    OrderResponse placeOrder(PlaceOrderRequest request);

    List<OrderResponse> getMyOrders();

    OrderResponse getMyOrderDetail(Integer orderId);

    void cancelMyOrder(Integer orderId);

    void cancelOrder(Integer orderId, CancelOrderRequest request);

    OrderStatusResponse trackOrderStatus(Integer orderId);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderDetail(Integer orderId);

    OrderResponse updateOrderStatus(Integer orderId, UpdateOrderStatusRequest request);

    void cancelOrderByStaff(Integer orderId);
}
