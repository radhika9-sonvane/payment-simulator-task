package com.tech.paymentSimualator.dto;

import lombok.Data;

@Data
public class PaymentInitiateRequest {
    private int amount;
    private String currency;
    private String description;
    private String userEmail;
}
