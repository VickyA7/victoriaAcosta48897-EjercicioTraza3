package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder

public class Provincia {
    private Long id;
    private String nombre;

    private Pais pais;

    @Builder.Default
    private Set<Localidad> localidades = new HashSet<>();
}
