package com.elmensajero.demo.domain.dto.response;

import com.elmensajero.demo.domain.enums.TamanioPaquete;
import com.elmensajero.demo.domain.enums.ZonaCaba;

import java.math.BigDecimal;

public record CotizacionResponse(

        ZonaCaba zonaOrigen,
        ZonaCaba zonaDestino,
        TamanioPaquete tamanio,
        Double pesoKg,
        BigDecimal precioBase,
        BigDecimal precioFinal,
        String detalle
) {}