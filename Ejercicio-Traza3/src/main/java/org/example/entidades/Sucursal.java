package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@SuperBuilder

public class Sucursal {
    private Long id;
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;

    private Empresa empresa;
    private Domicilio domicilio;
}
