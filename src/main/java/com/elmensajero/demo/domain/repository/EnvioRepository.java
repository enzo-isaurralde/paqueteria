package com.elmensajero.demo.domain.repository;

import com.elmensajero.demo.domain.entity.Envio;
import com.elmensajero.demo.domain.enums.EstadoEnvio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {

    Optional<Envio> findByNumeroSeguimiento(String numeroSeguimiento);

    List<Envio> findByEstado(EstadoEnvio estado);
}