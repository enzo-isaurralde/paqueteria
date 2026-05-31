package com.elmensajero.demo.domain.service;

import com.elmensajero.demo.domain.dto.request.CotizacionRequest;
import com.elmensajero.demo.domain.dto.response.CotizacionResponse;
import com.elmensajero.demo.domain.entity.TarifaZona;
import com.elmensajero.demo.domain.repository.TarifaZonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class CotizacionService {

    private final TarifaZonaRepository tarifaRepo;

    public CotizacionResponse cotizar(CotizacionRequest req) {
        TarifaZona tarifa = tarifaRepo
                .findByZonaOrigenAndZonaDestino(req.zonaOrigen(), req.zonaDestino())
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada para las zonas indicadas"));

        double factor = req.tamanio().getFactorPrecio();

        double pesoExcedente = Math.max(0, req.pesoKg() - req.tamanio().getPesoMaximoKg());
        double cargoExcedente = pesoExcedente * 150.0;

        BigDecimal precioFinal = tarifa.getPrecioBase()
                .multiply(BigDecimal.valueOf(factor))
                .add(BigDecimal.valueOf(cargoExcedente));

        if (Boolean.TRUE.equals(req.fragil())) {
            precioFinal = precioFinal.multiply(BigDecimal.valueOf(1.15));
        }

        precioFinal = precioFinal.setScale(2, RoundingMode.HALF_UP);

        String detalle = String.format(
                "Precio base $%.2f x factor %.1f (tamaño %s) + $%.2f (excedente peso) %s",
                tarifa.getPrecioBase(), factor, req.tamanio(),
                cargoExcedente,
                Boolean.TRUE.equals(req.fragil()) ? "+ 15% frágil" : ""
        );

        return new CotizacionResponse(
                req.zonaOrigen(),
                req.zonaDestino(),
                req.tamanio(),
                req.pesoKg(),
                tarifa.getPrecioBase(),
                precioFinal,
                detalle
        );
    }
}