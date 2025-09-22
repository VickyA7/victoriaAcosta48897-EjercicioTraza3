package org.example;

import org.example.entidades.*;
import org.example.repositorio.InMemoryRepository;

import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println(" ----------- PROBAMOS EL SISTEMA (TRAZA 1 + TRAZA 2) ----------");

        // -------------------------------
        // TRAZA 1: EMPRESAS, SUCURSALES, DOMICILIOS
        // -------------------------------
        InMemoryRepository<Empresa> empresaRepository = new InMemoryRepository<>();
        InMemoryRepository<SucursalArticulo> sucursalArticuloRepository = new InMemoryRepository<>();

        Pais argentina = Pais.builder().nombre("Argentina").build();

        Provincia buenosAires = Provincia.builder().id(1L).nombre("Buenos Aires").pais(argentina).build();
        Localidad caba = Localidad.builder().id(1L).nombre("CABA").provincia(buenosAires).build();
        Domicilio domicilio1 = Domicilio.builder().id(1L).calle("Calle 1").numero(100).cp(1000).localidad(caba).build();

        Localidad laPlata = Localidad.builder().id(2L).nombre("La Plata").provincia(buenosAires).build();
        Domicilio domicilio2 = Domicilio.builder().id(2L).calle("Calle 2").numero(200).cp(2000).localidad(laPlata).build();

        Provincia cordoba = Provincia.builder().id(2L).nombre("Córdoba").pais(argentina).build();
        Localidad cordobaCapital = Localidad.builder().id(3L).nombre("Córdoba Capital").provincia(cordoba).build();
        Domicilio domicilio3 = Domicilio.builder().id(3L).calle("Calle 3").numero(300).cp(3000).localidad(cordobaCapital).build();

        Localidad villaCarlosPaz = Localidad.builder().id(4L).nombre("Villa Carlos Paz").provincia(cordoba).build();
        Domicilio domicilio4 = Domicilio.builder().id(4L).calle("Calle 4").numero(400).cp(4000).localidad(villaCarlosPaz).build();

        Sucursal sucursal1 = Sucursal.builder().id(1L).nombre("Sucursal 1")
                .horarioApertura(LocalTime.of(9, 0)).horarioCierre(LocalTime.of(18, 0))
                .esCasaMatriz(true).domicilio(domicilio1).build();

        Sucursal sucursal2 = Sucursal.builder().id(2L).nombre("Sucursal 2")
                .horarioApertura(LocalTime.of(9, 0)).horarioCierre(LocalTime.of(18, 0))
                .esCasaMatriz(false).domicilio(domicilio2).build();

        Sucursal sucursal3 = Sucursal.builder().id(3L).nombre("Sucursal 3")
                .horarioApertura(LocalTime.of(9, 0)).horarioCierre(LocalTime.of(18, 0))
                .esCasaMatriz(true).domicilio(domicilio3).build();

        Sucursal sucursal4 = Sucursal.builder().id(4L).nombre("Sucursal 4")
                .horarioApertura(LocalTime.of(9, 0)).horarioCierre(LocalTime.of(18, 0))
                .esCasaMatriz(false).domicilio(domicilio4).build();

        Empresa empresa1 = Empresa.builder().id(1L).nombre("Empresa 1").razonSocial("Razon Social 1").cuit(1234567890)
                .sucursales(new HashSet<>(Set.of(sucursal1, sucursal2))).build();

        Empresa empresa2 = Empresa.builder().id(2L).nombre("Empresa 2").razonSocial("Razon Social 2").cuit(1222565602)
                .sucursales(new HashSet<>(Set.of(sucursal3, sucursal4))).build();

        sucursal1.setEmpresa(empresa1);
        sucursal2.setEmpresa(empresa1);
        sucursal3.setEmpresa(empresa2);
        sucursal4.setEmpresa(empresa2);

        empresaRepository.save(empresa1);
        empresaRepository.save(empresa2);

        // -------------------------------
        // TRAZA 2: CATEGORÍAS, INSUMOS, MANUFACTURADOS
        // -------------------------------
        InMemoryRepository<Categoria> categoriaRepository = new InMemoryRepository<>();
        InMemoryRepository<ArticuloInsumo> articuloInsumoRepository = new InMemoryRepository<>();
        InMemoryRepository<ArticuloManufacturado> articuloManufacturadoRepository = new InMemoryRepository<>();
        InMemoryRepository<UnidadMedida> unidadMedidaRepository = new InMemoryRepository<>();

        Categoria pizzas = Categoria.builder().denominacion("Pizzas").esInsumo(false).build();
        Categoria sandwiches = Categoria.builder().denominacion("Sandwiches").esInsumo(false).build();
        Categoria insumos = Categoria.builder().denominacion("Insumos").esInsumo(true).build();
        categoriaRepository.save(pizzas);
        categoriaRepository.save(sandwiches);
        categoriaRepository.save(insumos);

        UnidadMedida kg = UnidadMedida.builder().denominacion("Kg").build();
        UnidadMedida litro = UnidadMedida.builder().denominacion("Litro").build();
        UnidadMedida gramos = UnidadMedida.builder().denominacion("Gramos").build();
        unidadMedidaRepository.save(kg);
        unidadMedidaRepository.save(litro);
        unidadMedidaRepository.save(gramos);

        ArticuloInsumo sal = ArticuloInsumo.builder().denominacion("Sal").precioCompra(1.0).stockActual(100)
                .stockMinimo(10).stockMaximo(200).esParaElaborar(true).unidadMedida(gramos).categoria(insumos).build();
        ArticuloInsumo harina = ArticuloInsumo.builder().denominacion("Harina").precioCompra(0.5).stockActual(50)
                .stockMinimo(5).stockMaximo(100).esParaElaborar(true).unidadMedida(kg).categoria(insumos).build();
        articuloInsumoRepository.save(sal);
        articuloInsumoRepository.save(harina);

        ImagenArticulo img1 = ImagenArticulo.builder().name("HawaianaPizza1").url("pizza1").build();
        ImagenArticulo img2 = ImagenArticulo.builder().name("HawaianaPizza2").url("pizza2").build();

        ArticuloManufacturadoDetalle detallePizza1 = ArticuloManufacturadoDetalle.builder().cantidad(1).articuloInsumo(sal).build();
        ArticuloManufacturadoDetalle detallePizza2 = ArticuloManufacturadoDetalle.builder().cantidad(2).articuloInsumo(harina).build();

        ArticuloManufacturado pizzaHawaina = ArticuloManufacturado.builder()
                .denominacion("Pizza Hawaina").precioVenta(12.0).descripcion("Pizza con piña y jamón")
                .tiempoEstimadoMinutos(20).preparacion("Hornear 20 min")
                .categoria(pizzas).unidadMedida(kg)
                .imagenes(new HashSet<>(Set.of(img1, img2)))
                .articuloManufacturadoDetalles(new HashSet<>(Set.of(detallePizza1, detallePizza2)))
                .build();

        articuloManufacturadoRepository.save(pizzaHawaina);

        // -------------------------------
        // FUSIÓN: SUCURSAL <-> ARTÍCULOS
        // -------------------------------
        SucursalArticulo sa1 = SucursalArticulo.builder().sucursal(sucursal1).articulo(pizzaHawaina).stock(10).build();
        SucursalArticulo sa2 = SucursalArticulo.builder().sucursal(sucursal2).articulo(pizzaHawaina).stock(5).build();

        sucursalArticuloRepository.save(sa1);
        sucursalArticuloRepository.save(sa2);

        // -------------------------------
        // CONSULTAS / SALIDAS
        // -------------------------------
        System.out.println("\nEmpresas registradas:");
        empresaRepository.findAll().forEach(System.out::println);

        System.out.println("\nCategorías registradas:");
        categoriaRepository.findAll().forEach(System.out::println);

        System.out.println("\nArtículos manufacturados:");
        articuloManufacturadoRepository.findAll().forEach(System.out::println);

        System.out.println("\nArtículos por sucursal (SucursalArticulo):");
        sucursalArticuloRepository.findAll().forEach(System.out::println);

        System.out.println("\n--- FIN DE LA EJECUCIÓN ---");
    }
}