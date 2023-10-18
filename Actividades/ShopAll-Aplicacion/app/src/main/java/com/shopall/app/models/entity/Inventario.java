package com.shopall.app.models.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_Inventario")
public class Inventario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idInventario", nullable = false)
    private int idInventario;
    
    
    @Column(name = "Stock", nullable = true)
    private Integer stock;
    
    @ManyToOne
    @JoinColumn(name = "idVendedor", referencedColumnName = "idVendedor")
    private TiendaVendedor tblTiendaVendedorByIdVendedor;
    
    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    private Producto tblProductosByIdProducto;

}
