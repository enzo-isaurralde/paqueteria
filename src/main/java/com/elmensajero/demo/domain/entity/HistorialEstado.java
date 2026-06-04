package com.elmensajero.demo.domain.entity;

import com.elmensajero.demo.domain.enums.EstadoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_estados")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Audited
public class HistorialEstado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "envio_id")
    private Envio envio;

    @Enumerated(EnumType.STRING)
    private EstadoEnvio estadoAnterior;

    @Enumerated(EnumType.STRING)
    private EstadoEnvio estadoNuevo;

    @CreatedDate
    private LocalDateTime fechaCambio;

    @CreatedBy
    private String cambiadoPor;

    private String observacion;
}