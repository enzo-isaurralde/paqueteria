package com.elmensajero.demo.domain.repository;

import com.elmensajero.demo.domain.entity.HistorialEstado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialEstadoRepository extends JpaRepository<HistorialEstado, Long> {

    List<HistorialEstado> findByEnvioIdOrderByFechaCambioDesc(Long envioId);
}