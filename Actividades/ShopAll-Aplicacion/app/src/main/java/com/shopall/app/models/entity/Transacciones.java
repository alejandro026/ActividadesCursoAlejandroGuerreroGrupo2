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
@Table(name = "TBL_Transacciones")
public class Transacciones implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idTransaccion", nullable = false)
    private int idTransaccion;
    
    
    @Column(name = "Fecha", nullable = true, length = 45)
    private String fecha;
    
    @Column(name = "MontoTotal", nullable = true, precision = 0)
    private Double montoTotal;
    
    @Column(name = "DireccionEnvio", nullable = true, length = 110)
    private String direccionEnvio;
    
    @ManyToOne
    @JoinColumn(name = "idComprador", referencedColumnName = "idUsuario")
    private Usuario tblUsuariosByIdComprador;

}
