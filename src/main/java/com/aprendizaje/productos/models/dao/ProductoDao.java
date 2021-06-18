package com.aprendizaje.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.aprendizaje.microservices.commons.models.entities.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long> {

}