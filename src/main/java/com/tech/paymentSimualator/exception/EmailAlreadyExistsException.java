package com.tech.paymentSimualator.exception;

public class EmailAlreadyExistsException extends ApiErrorException {

    public EmailAlreadyExistsException(String email) {
        super("EMAIL_ALREADY_EXISTS", "Email address '" + email + "' already exists!", email);
    }
}