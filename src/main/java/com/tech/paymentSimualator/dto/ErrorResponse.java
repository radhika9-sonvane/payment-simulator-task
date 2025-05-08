package com.tech.paymentSimualator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String errorCode;
    private String message;
    private String details;

}
