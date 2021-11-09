package com.torre.tech.exception;

public class BadRequestException extends Exception {
    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
