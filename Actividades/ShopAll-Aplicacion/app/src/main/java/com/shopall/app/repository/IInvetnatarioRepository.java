package com.shopall.app.repository;

import com.shopall.app.models.entity.Inventario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Inteface que exteinde de JPA para el manejo de la base de datos
 */
@Repository
public interface IInvetnatarioRepository extends JpaRepository<Inventario, Integer> {
	Optional<Inventario> findByTblTiendaVendedorByIdVendedorIdVendedorAndTblProductosByIdProductoIdProducto(Integer idVendedor, Integer idProducto);
}
