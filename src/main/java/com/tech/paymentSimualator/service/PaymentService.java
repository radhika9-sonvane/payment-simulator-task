package com.tech.paymentSimualator.service;

import com.tech.paymentSimualator.dto.PaymentConfirmRequest;
import com.tech.paymentSimualator.dto.PaymentInitiateRequest;
import com.tech.paymentSimualator.dto.PaymentInitiateResponse;
import com.tech.paymentSimualator.entity.Payment;

public interface PaymentService {

    PaymentInitiateResponse initiatePayment(PaymentInitiateRequest request);

    String confirmPayment(PaymentConfirmRequest request);

    Payment getPayment(String paymentId);
}
