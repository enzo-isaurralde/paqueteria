package com.elmensajero.demo.domain.entity;

import com.elmensajero.demo.domain.enums.TamanioPaquete;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paquete {

    @Enumerated(EnumType.STRING)
    private TamanioPaquete tamanio;

    @Column(nullable = false)
    private Double pesoKg;

    private Double largocm;
    private Double anchocm;
    private Double altocm;

    private String descripcion;     // contenido declarado
    private Boolean fragil = false;
}