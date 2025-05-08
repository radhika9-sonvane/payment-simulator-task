package com.tech.paymentSimualator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentInitiateResponse {
    private String paymentId;
    private String paymentLink;
}