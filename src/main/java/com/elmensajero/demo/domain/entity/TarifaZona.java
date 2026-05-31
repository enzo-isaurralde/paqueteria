package com.elmensajero.demo.domain.entity;

import com.elmensajero.demo.domain.enums.ZonaCaba;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "tarifas_zona",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"zona_origen", "zona_destino"}
        )
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarifaZona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "zona_origen", nullable = false)
    private ZonaCaba zonaOrigen;

    @Enumerated(EnumType.STRING)
    @Column(name = "zona_destino", nullable = false)
    private ZonaCaba zonaDestino;

    @Column(name = "precio_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioBase;
}