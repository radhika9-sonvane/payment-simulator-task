package com.tech.paymentSimualator.dto;

import lombok.Data;

@Data
public class PaymentConfirmRequest {
    private String paymentId;
    private String status;
}
