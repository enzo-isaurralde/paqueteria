package com.elmensajero.demo.domain.repository;

import com.elmensajero.demo.domain.entity.TarifaZona;
import com.elmensajero.demo.domain.enums.ZonaCaba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarifaZonaRepository extends JpaRepository<TarifaZona, Long> {

    Optional<TarifaZona> findByZonaOrigenAndZonaDestino(ZonaCaba zonaOrigen, ZonaCaba zonaDestino);
}