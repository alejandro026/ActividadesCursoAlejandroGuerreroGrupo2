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
@Table(name = "TBL_Lista_Compras")
public class ListaCompras implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idLista", nullable = false)
    private int idLista;
    
    
    @Column(name = "Cantidad", nullable = true)
    private Integer cantidad;
    
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuarios tblUsuariosByIdUsuario;
    
    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "idProducto")
    private Productos tblProductosByIdProducto;

}
