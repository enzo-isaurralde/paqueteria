package com.elmensajero.demo.domain.dto.request;

import com.elmensajero.demo.domain.enums.TamanioPaquete;
import com.elmensajero.demo.domain.enums.ZonaCaba;
import jakarta.validation.constraints.NotNull;

public record CotizacionRequest(

        @NotNull(message = "La zona de origen es obligatoria")
        ZonaCaba zonaOrigen,

        @NotNull(message = "La zona de destino es obligatoria")
        ZonaCaba zonaDestino,

        @NotNull(message = "El tamaño es obligatorio")
        TamanioPaquete tamanio,

        @NotNull(message = "El peso es obligatorio")
        Double pesoKg,

        @NotNull(message = "Indicar si es frágil")
        Boolean fragil
) {}