package com.elmensajero.demo.domain.entity;

import com.elmensajero.demo.domain.enums.EstadoEnvio;
import com.elmensajero.demo.domain.enums.ZonaCaba;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "envios")
@Audited                          // Hibernate Envers — audita todos los cambios
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String numeroSeguimiento;   // Ej: "EMS-20240417-000123"

    // --- Producto / paquete ---
    @Embedded
    private Paquete paquete;

    // --- Origen / Destino ---
    @Enumerated(EnumType.STRING)
    private ZonaCaba zonaOrigen;

    @Enumerated(EnumType.STRING)
    private ZonaCaba zonaDestino;

    private String direccionOrigen;
    private String direccionDestino;

    // Datos del remitente
    private String nombreRemitente;
    private String telefonoRemitente;
    private String emailRemitente;

    // Datos del destinatario
    private String nombreDestinatario;
    private String telefonoDestinatario;
    private String emailDestinatario;

    // --- Estado ---
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEnvio estado = EstadoEnvio.RECIBIDO;

    // --- Cotización ---
    @Column(precision = 10, scale = 2)
    private BigDecimal montoCotizado;

    // --- Auditoría automática (Spring Data JPA) ---
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

    // Historial de estados (relación uno a muchos)
    @OneToMany(mappedBy = "envio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HistorialEstado> historialEstados = new ArrayList<>();
}