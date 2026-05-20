package com.elmensajero.demo.domain.service;

import com.elmensajero.demo.domain.dto.request.CotizacionRequest;
import com.elmensajero.demo.domain.entity.TarifaZona;
import com.elmensajero.demo.domain.repository.TarifaZonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CotizacionService {

    private final TarifaZonaRepository tarifaRepo;

    public BigDecimal cotizar(CotizacionRequest req) {
        TarifaZona tarifa = tarifaRepo
                .findByZonaOrigenAndZonaDestino(req.getZonaOrigen(), req.getZonaDestino())
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada"));

        double factor = req.getTamanio().getFactorPrecio();

        // Ajuste adicional por peso excedente
        double pesoExcedente = Math.max(0,
                req.getPesoKg() - req.getTamanio().getPesoMaximoKg());
        double cargoExcedente = pesoExcedente * 150.0; // $150 por kg extra

        BigDecimal total = tarifa.getPrecioBase()
                .multiply(BigDecimal.valueOf(factor))
                .add(BigDecimal.valueOf(cargoExcedente));

        // Fragil: +15%
        if (Boolean.TRUE.equals(req.getFragil())) {
            total = total.multiply(BigDecimal.valueOf(1.15));
        }

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}