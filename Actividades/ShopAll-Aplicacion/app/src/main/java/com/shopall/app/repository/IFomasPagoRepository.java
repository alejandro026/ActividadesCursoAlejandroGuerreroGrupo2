package com.shopall.app.repository;

import com.shopall.app.models.entity.FormasPago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface IFomasPagoRepository extends JpaRepository<FormasPago, Integer> {
}
