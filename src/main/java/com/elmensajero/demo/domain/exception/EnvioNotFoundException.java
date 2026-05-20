package com.elmensajero.demo.domain.exception;

// src/main/java/com/elmensajero/demo/elmensajero/domain/exception/EnvioNotFoundException.java


public class EnvioNotFoundException extends RuntimeException {

    public EnvioNotFoundException(String numeroSeguimiento) {
        super("Envío no encontrado con número de seguimiento: " + numeroSeguimiento);
    }
}