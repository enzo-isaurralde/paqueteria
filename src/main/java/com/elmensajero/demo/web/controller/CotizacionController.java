package com.elmensajero.demo.web.controller;

import com.elmensajero.demo.domain.dto.request.CotizacionRequest;
import com.elmensajero.demo.domain.dto.response.CotizacionResponse;
import com.elmensajero.demo.domain.service.CotizacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cotizacion")
@RequiredArgsConstructor
public class CotizacionController {

    private final CotizacionService cotizacionService;

    @PostMapping
    public ResponseEntity<CotizacionResponse> cotizar(@Valid @RequestBody CotizacionRequest request) {
        return ResponseEntity.ok(cotizacionService.cotizar(request));
    }
}