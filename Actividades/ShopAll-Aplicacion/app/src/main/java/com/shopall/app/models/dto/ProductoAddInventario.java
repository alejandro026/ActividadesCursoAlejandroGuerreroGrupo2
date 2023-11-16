package com.shopall.app.models.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Setter
@Getter
@Validated
public class ProductoAddInventario {

    private Integer idInventario;

    @NotNull(message = "Debe ingresar un id de producto valido")
    @Positive(message = "Debe ingresar un id de producto valido")
    private Integer idProducto;

    @NotNull(message = "Debe ingresar una cantidad valida")
    @Positive(message = "Debe ingresar una cantidad valida")
    private Integer stock;

    @NotNull(message = "Debe ingresar una vendedor valida")
    @Positive(message = "Debe ingresar una vendedor valida")
    private Integer idVendedor;

}
