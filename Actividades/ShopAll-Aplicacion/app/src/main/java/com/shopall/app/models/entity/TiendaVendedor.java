package com.shopall.app.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "TBL_Tienda_Vendedor")
public class TiendaVendedor implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idVendedor", nullable = false)
    private int idVendedor;
    
    @Column(name = "Nombre", nullable = true, length = 45)
    private String nombre;
    
    @Column(name = "Direccion", nullable = true, length = 45)
    private String direccion;
    
    @Column(name = "Telefono", nullable = true, length = 45)
    private String telefono;
    
    @Column(name = "Correo", nullable = true, length = 45)
    private String correo;

}
