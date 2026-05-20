package com.elmensajero.demo.domain.enums;

public enum EstadoEnvio {
    EN_CAMINO("En camino"),
    RECIBIDO("Recibido en sede"),
    ENTREGADO("Entregado al destinatario");
    private final String descripcion;
    EstadoEnvio(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }
   }