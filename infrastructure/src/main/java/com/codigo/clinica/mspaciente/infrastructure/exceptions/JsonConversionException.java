package com.codigo.clinica.mspaciente.infrastructure.exceptions;

public class JsonConversionException extends RuntimeException{
    public JsonConversionException(String msj, Throwable cause){
        super(msj, cause);
    }
}
