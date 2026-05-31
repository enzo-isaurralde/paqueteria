package com.elmensajero.demo.domain.dto.request;

import com.elmensajero.demo.domain.enums.TamanioPaquete;
import com.elmensajero.demo.domain.enums.ZonaCaba;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnvioRequest(

        @NotNull ZonaCaba zonaOrigen,
        @NotNull ZonaCaba zonaDestino,

        @NotBlank String direccionOrigen,
        @NotBlank String direccionDestino,

        @NotBlank String nombreRemitente,
        @NotBlank String telefonoRemitente,
        String emailRemitente,

        @NotBlank String nombreDestinatario,
        @NotBlank String telefonoDestinatario,
        String emailDestinatario,

        @NotNull PaqueteDTO paquete
) {
    public record PaqueteDTO(
            @NotNull TamanioPaquete tamanio,
            @NotNull Double pesoKg,
            String descripcion,
            Boolean fragil
    ) {}
}