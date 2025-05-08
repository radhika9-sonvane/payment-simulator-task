package com.tech.paymentSimualator.service.impl;

import com.tech.paymentSimualator.dto.PaymentConfirmRequest;
import com.tech.paymentSimualator.dto.PaymentInitiateRequest;
import com.tech.paymentSimualator.dto.PaymentInitiateResponse;
import com.tech.paymentSimualator.entity.Payment;
import com.tech.paymentSimualator.entity.User;
import com.tech.paymentSimualator.repository.PaymentRepository;
import com.tech.paymentSimualator.repository.UserRepository;
import com.tech.paymentSimualator.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public PaymentInitiateResponse initiatePayment(PaymentInitiateRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        String paymentId = UUID.randomUUID().toString();

        Payment payment = new Payment();
        payment.setPaymentId(paymentId);
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setStatus("PENDING");
        payment.setDescription(request.getDescription());

        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        payment.setUser(user);

        paymentRepository.save(payment);

        String paymentLink = "https://mock.pay/confirm/" + paymentId;

        return new PaymentInitiateResponse(paymentId, paymentLink);
    }

    public String confirmPayment(PaymentConfirmRequest request) {
        Payment payment = paymentRepository.findById(request.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Invalid payment ID"));

        if (!"PENDING".equals(payment.getStatus())) {
            return "Payment already processed with status: " + payment.getStatus();
        }

        if ("SUCCESS".equalsIgnoreCase(request.getStatus()) || "FAILED".equalsIgnoreCase(request.getStatus())) {
            payment.setStatus(request.getStatus().toUpperCase());
            paymentRepository.save(payment);
            return "Payment is: " + payment.getStatus();
        } else {
            return "Invalid status. Allowed: SUCCESS, FAILED";
        }
    }

    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow();
    }
}
