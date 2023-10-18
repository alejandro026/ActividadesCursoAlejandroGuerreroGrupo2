package com.shopall.app.models.dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
public class ProductoDTO {

    private Integer idProducto;

    @NotBlank(message = "Debe ingresar una nombre valido")
    private String nombre;

    @NotBlank(message = "Debe ingresar una nombre valido")
    private String detallesProducto;

    @NotNull(message = "Debe ingresar un precio")
    @Positive(message = "Debe ingresar un precio valido")
    private double precio;

    @NotNull(message = "Debe ingresar una categoria")
    @Positive(message = "Debe ingresar una categoria valida")
    private Integer idCategoria;

    @NotNull(message = "Debe ingresar un vendedor")
    @Positive(message = "Debe ingresar un vendedor valido")
    private Integer idVendedor;

    @NotBlank(message = "Debe ingresar una descripcion")
    private String descripcion;

}
