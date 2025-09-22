package org.example.entidades;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString (exclude = "sucursales")//Excluir sucursales para evitar recursion!! Ya que hay una bidireccionalidad
@SuperBuilder

public class Empresa {
    private Long id;
    private String nombre;
    private String razonSocial;
    private Integer cuit;
    private String logo;

    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();

}

//Si no pongo el exclude tendria que trabajar asi
/*
@Override
public String toString() {
    return "Empresa{ " + " id= " + id +
                ", nombre='" + nombre + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", cuil=" + cuil +
                '}';
    }
    "
 */
