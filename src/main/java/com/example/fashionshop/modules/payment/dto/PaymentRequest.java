package com.example.fashionshop.modules.payment.dto;

import com.example.fashionshop.common.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotNull(message = "Order ID is required")
    private Integer orderId;

    @NotNull(message = "Payment method is required")
    private PaymentMethod paymentMethod;
}
