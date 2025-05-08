package com.tech.paymentSimualator.service.impl;

import com.tech.paymentSimualator.dto.AuthRequest;
import com.tech.paymentSimualator.dto.AuthResponse;
import com.tech.paymentSimualator.dto.RegisterRequest;
import com.tech.paymentSimualator.entity.User;
import com.tech.paymentSimualator.exception.EmailAlreadyExistsException;
import com.tech.paymentSimualator.repository.UserRepository;
import com.tech.paymentSimualator.service.AuthService;
import com.tech.paymentSimualator.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        if (isEmailAlreadyExist(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }

    private boolean isEmailAlreadyExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
