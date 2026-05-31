package com.elmensajero.demo.domain.mapper;

import com.elmensajero.demo.domain.dto.request.EnvioRequest;
import com.elmensajero.demo.domain.dto.response.EnvioResponse;
import com.elmensajero.demo.domain.entity.Envio;
import com.elmensajero.demo.domain.entity.Paquete;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EnvioMapper {

    @Mapping(source = "paquete.tamanio", target = "tamanio")
    @Mapping(source = "paquete.pesoKg", target = "pesoKg")
    @Mapping(source = "paquete.fragil", target = "fragil")
    @Mapping(source = "paquete.descripcion", target = "descripcionPaquete")
    EnvioResponse toResponse(Envio envio);

    @Mapping(source = "paquete.tamanio", target = "paquete.tamanio")
    @Mapping(source = "paquete.pesoKg", target = "paquete.pesoKg")
    @Mapping(source = "paquete.descripcion", target = "paquete.descripcion")
    @Mapping(source = "paquete.fragil", target = "paquete.fragil")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "numeroSeguimiento", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "montoCotizado", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaUltimaModificacion", ignore = true)
    @Mapping(target = "creadoPor", ignore = true)
    @Mapping(target = "modificadoPor", ignore = true)
    @Mapping(target = "historialEstados", ignore = true)
    Envio toEntity(EnvioRequest request);

    default Paquete map(EnvioRequest.PaqueteDTO dto) {
        if (dto == null) return null;
        return Paquete.builder()
                .tamanio(dto.tamanio())
                .pesoKg(dto.pesoKg())
                .descripcion(dto.descripcion())
                .fragil(dto.fragil() != null ? dto.fragil() : false)
                .build();
    }
}