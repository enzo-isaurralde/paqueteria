package com.elmensajero.demo.config;

import com.elmensajero.demo.domain.entity.Zona;
import com.elmensajero.demo.domain.enums.ZonaCaba;
import com.elmensajero.demo.domain.repository.ZonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final ZonaRepository zonaRepo;

    @Override
    public void run(ApplicationArguments args) {
        if (zonaRepo.count() > 0) return; // ya cargado

        zonaRepo.saveAll(List.of(
                zona(ZonaCaba.COMUNA_1,  "Comuna 1",  List.of("Retiro", "San Nicolás", "Puerto Madero", "San Telmo", "Montserrat", "Constitución")),
                zona(ZonaCaba.COMUNA_2,  "Comuna 2",  List.of("Recoleta")),
                zona(ZonaCaba.COMUNA_3,  "Comuna 3",  List.of("Balvanera", "San Cristóbal")),
                zona(ZonaCaba.COMUNA_4,  "Comuna 4",  List.of("La Boca", "Barracas", "Parque Patricios", "Nueva Pompeya")),
                zona(ZonaCaba.COMUNA_5,  "Comuna 5",  List.of("Almagro", "Boedo")),
                zona(ZonaCaba.COMUNA_6,  "Comuna 6",  List.of("Caballito")),
                zona(ZonaCaba.COMUNA_7,  "Comuna 7",  List.of("Flores", "Parque Chacabuco")),
                zona(ZonaCaba.COMUNA_8,  "Comuna 8",  List.of("Villa Soldati", "Villa Riachuelo", "Villa Lugano")),
                zona(ZonaCaba.COMUNA_9,  "Comuna 9",  List.of("Liniers", "Mataderos", "Parque Avellaneda")),
                zona(ZonaCaba.COMUNA_10, "Comuna 10", List.of("Villa Real", "Monte Castro", "Versalles", "Floresta", "Vélez Sársfield", "Villa Luro")),
                zona(ZonaCaba.COMUNA_11, "Comuna 11", List.of("Villa Gral. Mitre", "Villa Devoto", "Villa del Parque", "Villa Santa Rita")),
                zona(ZonaCaba.COMUNA_12, "Comuna 12", List.of("Coghlan", "Saavedra", "Villa Urquiza", "Villa Pueyrredón")),
                zona(ZonaCaba.COMUNA_13, "Comuna 13", List.of("Núñez", "Belgrano", "Colegiales")),
                zona(ZonaCaba.COMUNA_14, "Comuna 14", List.of("Palermo")),
                zona(ZonaCaba.COMUNA_15, "Comuna 15", List.of("Chacarita", "Villa Crespo", "La Paternal", "Villa Ortúzar", "Agronomía", "Parque Chas"))
        ));

        System.out.println("✅ Zonas de CABA cargadas correctamente.");
    }

    private Zona zona(ZonaCaba zonaCaba, String nombre, List<String> barrios) {
        return Zona.builder()
                .zonaCaba(zonaCaba)
                .nombre(nombre)
                .barrios(barrios)
                .build();
    }
}