package com.elmensajero.demo.web.controller;

import com.elmensajero.demo.domain.dto.response.EnvioResponse;
import com.elmensajero.demo.domain.entity.HistorialEstado;
import com.elmensajero.demo.domain.mapper.EnvioMapper;
import com.elmensajero.demo.domain.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
@RequiredArgsConstructor
public class TrackingController {

    private final TrackingService trackingService;
    private final EnvioMapper envioMapper;

    @GetMapping("/{numeroSeguimiento}")
    public ResponseEntity<EnvioResponse> rastrear(@PathVariable String numeroSeguimiento) {
        return ResponseEntity.ok(
                envioMapper.toResponse(trackingService.buscarPorTracking(numeroSeguimiento))
        );
    }

    @GetMapping("/{numeroSeguimiento}/historial")
    public ResponseEntity<List<HistorialEstado>> historial(@PathVariable String numeroSeguimiento) {
        var envio = trackingService.buscarPorTracking(numeroSeguimiento);
        return ResponseEntity.ok(trackingService.obtenerHistorial(envio.getId()));
    }
}