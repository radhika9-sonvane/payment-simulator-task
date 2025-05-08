package com.tech.paymentSimualator.controller;

import com.tech.paymentSimualator.dto.PaymentConfirmRequest;
import com.tech.paymentSimualator.dto.PaymentInitiateRequest;
import com.tech.paymentSimualator.dto.PaymentInitiateResponse;
import com.tech.paymentSimualator.entity.Payment;
import com.tech.paymentSimualator.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Endpoint for initiating the payment
    @PostMapping("/initiate")
    public ResponseEntity<PaymentInitiateResponse> initiate(@RequestBody PaymentInitiateRequest request) {
        PaymentInitiateResponse response = paymentService.initiatePayment(request);
        return ResponseEntity.ok(response);
    }

    // Endpoint for confirming the payment
    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestBody PaymentConfirmRequest request) {
        return ResponseEntity.ok(paymentService.confirmPayment(request));
    }

    // Endpoint for getting the payment details by paymentId
    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> get(@PathVariable String paymentId) {
        return ResponseEntity.ok(paymentService.getPayment(paymentId));
    }
}
