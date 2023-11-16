package com.shopall.app.models.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_Lista_Compras")
public class ListaCompras implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ListaComprasId id;

    @MapsId("idCompra")
    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @MapsId("idProducto")
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(name = "total", nullable = false)
    private Double total;

}
