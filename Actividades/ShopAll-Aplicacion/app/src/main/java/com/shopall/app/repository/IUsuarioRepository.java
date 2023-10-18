package com.shopall.app.repository;

import com.shopall.app.models.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * Inteface que exteinde de JPA para el manejo de la base de datos
 */
@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
