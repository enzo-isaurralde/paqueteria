package com.elmensajero.demo.domain.entity;

import com.elmensajero.demo.domain.enums.ZonaCaba;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "zonas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "zona_caba", nullable = false, unique = true)
    private ZonaCaba zonaCaba;

    @ElementCollection
    @CollectionTable(
            name = "zona_barrios",
            joinColumns = @JoinColumn(name = "zona_id")
    )
    @Column(name = "barrio")
    private List<String> barrios;
}