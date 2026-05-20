package com.elmensajero.demo.domain.repository;

import java.lang.ScopedValue;

public interface EnvioRepository {
    <T> ScopedValue<T> findByNumeroSeguimiento(String numeroSeguimiento);
}
