package com.codigo.clinica.mspaciente.infrastructure.exceptions;

public class ResponseValidationException extends RuntimeException {
    public ResponseValidationException(String message) {
        super(message);
    }
}