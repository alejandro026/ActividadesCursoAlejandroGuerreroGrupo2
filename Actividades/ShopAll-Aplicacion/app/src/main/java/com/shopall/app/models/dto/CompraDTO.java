/**
 * 
 */
package com.shopall.app.models.dto;

import java.util.Date;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.shopall.app.models.entity.FormasPago;
import com.shopall.app.models.entity.Usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Alejandro Guerrero
 *
 */
@Setter
@Getter
@Validated
public class CompraDTO {
	
    private Integer idCompra;
    
    private Date fecha;
    
    private Integer cantidadProductos;
    
    private Double total;
    
    @NotNull(message = "Debe ingresar un usuario")
    @Positive(message = "Debe ingresar un usuario valido")
    private Integer idUsuario;
    
    @NotNull(message = "Debe ingresar una forma de pago")
    @Positive(message = "Debe ingresar una forma de pago valida")
    private Integer idFormaPago;
    
    @NotNull(message = "Debe ingresar un vendedor")
    @Positive(message = "Debe ingresar un vendedor valido")
    private Integer idVendedor;
    
    @Valid
    private List<ProductoAddDTO> productos;







}
