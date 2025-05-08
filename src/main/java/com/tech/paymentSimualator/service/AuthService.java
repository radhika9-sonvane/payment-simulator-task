package com.tech.paymentSimualator.service;

import com.tech.paymentSimualator.dto.AuthRequest;
import com.tech.paymentSimualator.dto.AuthResponse;
import com.tech.paymentSimualator.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(AuthRequest request);
}
