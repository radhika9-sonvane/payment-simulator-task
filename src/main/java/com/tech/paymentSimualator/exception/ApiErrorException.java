package com.tech.paymentSimualator.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiErrorException extends RuntimeException {

    private String errorCode;
    private String details;

    public ApiErrorException(String errorCode, String message, String details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }

}