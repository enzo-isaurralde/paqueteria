package com.elmensajero.demo.domain.dto.response;

import com.elmensajero.demo.domain.enums.EstadoEnvio;
import com.elmensajero.demo.domain.enums.TamanioPaquete;
import com.elmensajero.demo.domain.enums.ZonaCaba;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EnvioResponse(

        Long id,
        String numeroSeguimiento,
        EstadoEnvio estado,

        ZonaCaba zonaOrigen,
        ZonaCaba zonaDestino,
        String direccionOrigen,
        String direccionDestino,

        String nombreRemitente,
        String telefonoRemitente,
        String emailRemitente,

        String nombreDestinatario,
        String telefonoDestinatario,
        String emailDestinatario,

        TamanioPaquete tamanio,
        Double pesoKg,
        Boolean fragil,
        String descripcionPaquete,

        BigDecimal montoCotizado,
        LocalDateTime fechaCreacion,
        String creadoPor
) {}