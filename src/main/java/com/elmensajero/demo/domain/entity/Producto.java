package com.elmensajero.demo.domain.entity;

import com.elmensajero.demo.domain.enums.TamanioPaquete;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "productos")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private Double pesoKg;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TamanioPaquete tamanio;

    @Column(nullable = false)
    private Boolean fragil = false;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

    @LastModifiedDate
    private LocalDateTime fechaUltimaModificacion;

    @CreatedBy
    @Column(updatable = false)
    private String creadoPor;

    @LastModifiedBy
    private String modificadoPor;
}
