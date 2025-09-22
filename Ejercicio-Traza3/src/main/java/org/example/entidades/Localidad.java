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

public class Localidad {
    private Long id;
    private String nombre;

    private Provincia provincia;

    @Builder.Default
    private Set<Domicilio> domicilios = new HashSet<>();
}
