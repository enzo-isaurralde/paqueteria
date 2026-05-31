package com.elmensajero.demo.domain.service;

import com.elmensajero.demo.domain.dto.request.CotizacionRequest;
import com.elmensajero.demo.domain.dto.request.EnvioRequest;
import com.elmensajero.demo.domain.dto.response.EnvioResponse;
import com.elmensajero.demo.domain.entity.Envio;
import com.elmensajero.demo.domain.entity.HistorialEstado;
import com.elmensajero.demo.domain.enums.EstadoEnvio;
import com.elmensajero.demo.domain.exception.EnvioNotFoundException;
import com.elmensajero.demo.domain.mapper.EnvioMapper;
import com.elmensajero.demo.domain.repository.EnvioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnvioService {

    private final EnvioRepository envioRepo;
    private final EnvioMapper envioMapper;
    private final CotizacionService cotizacionService;
    private final TrackingNumberGenerator trackingNumberGenerator;

    @Transactional
    public EnvioResponse crear(EnvioRequest request) {

        CotizacionRequest cotizacionReq = new CotizacionRequest(
                request.zonaOrigen(),
                request.zonaDestino(),
                request.paquete().tamanio(),
                request.paquete().pesoKg(),
                request.paquete().fragil() != null ? request.paquete().fragil() : false
        );

        var cotizacion = cotizacionService.cotizar(cotizacionReq);

        Envio envio = envioMapper.toEntity(request);
        envio.setNumeroSeguimiento(trackingNumberGenerator.generar());
        envio.setEstado(EstadoEnvio.RECIBIDO);
        envio.setMontoCotizado(cotizacion.precioFinal());

        HistorialEstado historialInicial = HistorialEstado.builder()
                .envio(envio)
                .estadoAnterior(null)
                .estadoNuevo(EstadoEnvio.RECIBIDO)
                .observacion("Envío creado")
                .build();

        envio.getHistorialEstados().add(historialInicial);

        return envioMapper.toResponse(envioRepo.save(envio));
    }

    @Transactional(readOnly = true)
    public EnvioResponse obtenerPorId(Long id) {
        Envio envio = envioRepo.findById(id)
                .orElseThrow(() -> new EnvioNotFoundException("ID: " + id));
        return envioMapper.toResponse(envio);
    }

    @Transactional(readOnly = true)
    public Page<EnvioResponse> listar(Pageable pageable) {
        return envioRepo.findAll(pageable).map(envioMapper::toResponse);
    }

    @Transactional
    public EnvioResponse cambiarEstado(String numeroSeguimiento,
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

        return envioMapper.toResponse(envioRepo.save(envio));
    }
}