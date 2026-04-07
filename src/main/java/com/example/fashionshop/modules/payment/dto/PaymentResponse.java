package com.example.fashionshop.modules.payment.dto;

import com.example.fashionshop.common.enums.PaymentMethod;
import com.example.fashionshop.common.enums.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponse {
    private Integer paymentId;
    private Integer orderId;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime paidAt;
}
