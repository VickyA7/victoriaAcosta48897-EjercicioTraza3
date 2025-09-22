package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
@SuperBuilder
public class SucursalArticulo {
    private Long id;
    private Sucursal sucursal;
    private Articulo articulo;
    private double precio;
    private int stock;
}
