package com.aprendizaje.productos.models.service;

import java.util.List;

import com.aprendizaje.microservices.commons.models.entities.Producto;

public interface IProductoService {

	List<Producto> findAll();

	Producto findById(Long id);

	Producto save(Producto producto);

	void deleteById(Long id);

}