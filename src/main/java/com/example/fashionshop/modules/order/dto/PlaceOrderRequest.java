package com.example.fashionshop.modules.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PlaceOrderRequest {
    @NotBlank(message = "Receiver name is required")
    private String receiverName;

    @NotBlank(message = "Phone is required")
    private String phone;

    @NotBlank(message = "Shipping address is required")
    private String shippingAddress;
}
