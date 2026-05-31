package com.elmensajero.demo.domain.service;

import com.elmensajero.demo.domain.entity.Envio;
import com.elmensajero.demo.domain.entity.HistorialEstado;
import com.elmensajero.demo.domain.enums.EstadoEnvio;
import com.elmensajero.demo.domain.exception.EnvioNotFoundException;
import com.elmensajero.demo.domain.repository.EnvioRepository;
import com.elmensajero.demo.domain.repository.HistorialEstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingService {

    private final EnvioRepository envioRepo;
    private final HistorialEstadoRepository historialRepo;

    public Envio cambiarEstado(String numeroSeguimiento,
                               EstadoEnvio nuevoEstado,
                               String observacion) {
        Envio envio = envioRepo.findByNumeroSeguimiento(numeroSeguimiento)
                .orElseThrow(() -> new EnvioNotFoundException(numeroSeguimiento));

        HistorialEstado historial = HistorialEstado.builder()
                .envio(envio)
                .estadoAnterior(envio.getEstado())
                .estadoNuevo(nuevoEstado)
                .observacion(observacion)
                .build();

        envio.setEstado(nuevoEstado);
        envio.getHistorialEstados().add(historial);

        return envioRepo.save(envio);  // cascade guarda el historial
    }

    public Envio buscarPorTracking(String numeroSeguimiento) {
        return envioRepo.findByNumeroSeguimiento(numeroSeguimiento)
                .orElseThrow(() -> new EnvioNotFoundException(numeroSeguimiento));
    }
    @Transactional(readOnly = true)
    public List<HistorialEstado> obtenerHistorial(Long envioId) {
        return historialRepo.findByEnvioIdOrderByFechaCambioDesc(envioId);
    }
}