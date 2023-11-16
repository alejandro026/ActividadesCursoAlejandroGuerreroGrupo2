package com.shopall.app.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_Productos")
public class Producto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idProducto", nullable = false)
    private Integer idProducto;
    
    @Column(name = "Nombre", nullable = true, length = 45)
    private String nombre;
    
    @Column(name = "Descripcion", nullable = true, length = 45)
    private String descripcion;
    
    
    @Column(name = "DetallesProducto", nullable = true, length = 45)
    private String detallesProducto;
    
    
    @Column(name = "Precio", nullable = false, precision = 0)
    private double precio;

    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategoria")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name = "idVendedor", referencedColumnName = "idVendedor")
    private TiendaVendedor tiendaVendedor;

}
