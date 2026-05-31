package com.elmensajero.demo.web.controller;

import com.elmensajero.demo.domain.dto.request.EnvioRequest;
import com.elmensajero.demo.domain.dto.response.EnvioResponse;
import com.elmensajero.demo.domain.entity.HistorialEstado;
import com.elmensajero.demo.domain.enums.EstadoEnvio;
import com.elmensajero.demo.domain.service.EnvioService;
import com.elmensajero.demo.domain.service.TrackingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/envios")
@RequiredArgsConstructor
public class EnvioController {

    private final EnvioService envioService;
    private final TrackingService trackingService;

    @PostMapping
    public ResponseEntity<EnvioResponse> crear(@Valid @RequestBody EnvioRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(envioService.crear(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(envioService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<Page<EnvioResponse>> listar(Pageable pageable) {
        return ResponseEntity.ok(envioService.listar(pageable));
    }

    @PatchMapping("/{numeroSeguimiento}/estado")
    public ResponseEntity<EnvioResponse> cambiarEstado(
            @PathVariable String numeroSeguimiento,
            @RequestParam EstadoEnvio nuevoEstado,
            @RequestParam(required = false) String observacion) {
        return ResponseEntity.ok(envioService.cambiarEstado(numeroSeguimiento, nuevoEstado, observacion));
    }

    @GetMapping("/{id}/historial")
    public ResponseEntity<List<HistorialEstado>> obtenerHistorial(@PathVariable Long id) {
        return ResponseEntity.ok(trackingService.obtenerHistorial(id));
    }
}