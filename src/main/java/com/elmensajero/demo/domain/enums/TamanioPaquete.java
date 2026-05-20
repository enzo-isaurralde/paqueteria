package com.elmensajero.demo.domain.enums;

public enum TamanioPaquete {
    PEQUENIO(1.0, 1.0),
    MEDIANO(5.0, 1.5),
    GRANDE(15.0, 2.2),
    EXTRA_GRANDE(30.0, 3.0);

    private final double pesoMaximoKg;
    private final double factorPrecio;

    TamanioPaquete(double pesoMaximoKg, double factorPrecio) {
        this.pesoMaximoKg = pesoMaximoKg;
        this.factorPrecio = factorPrecio;
    }
}