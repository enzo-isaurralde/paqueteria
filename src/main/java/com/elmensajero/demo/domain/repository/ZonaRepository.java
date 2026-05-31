package com.elmensajero.demo.domain.repository;

import com.elmensajero.demo.domain.entity.Zona;
import com.elmensajero.demo.domain.enums.ZonaCaba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZonaRepository extends JpaRepository<Zona, Long> {

    Optional<Zona> findByZonaCaba(ZonaCaba zonaCaba);
}