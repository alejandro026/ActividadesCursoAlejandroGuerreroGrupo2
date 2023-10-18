package com.shopall.app.services;

import com.shopall.app.models.dto.ProductoDTO;
import com.shopall.app.models.entity.Response;

public interface IProductosService {
    Response<ProductoDTO> guardarProducto(ProductoDTO productoDto);
}
