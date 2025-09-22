package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@SuperBuilder

public class Domicilio {
    private Long id;
    private String calle;
    private Integer numero;
    private Integer cp;

    private Localidad localidad;
    //podriamos colocar como atributo a Sucursal pero se genera bidireccionalidad
    //private Domicilio domicilio;
}


