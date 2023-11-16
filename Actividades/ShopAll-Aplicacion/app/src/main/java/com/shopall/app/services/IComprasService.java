package com.shopall.app.services;

import com.shopall.app.models.dto.CompraDTO;
import com.shopall.app.models.entity.Response;

public interface IComprasService {
	
	public Response<CompraDTO> generarCompra(CompraDTO compraDto);
}
