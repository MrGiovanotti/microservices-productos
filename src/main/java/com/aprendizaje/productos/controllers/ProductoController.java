package com.aprendizaje.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aprendizaje.microservices.commons.models.entities.Producto;
import com.aprendizaje.productos.models.service.IProductoService;

@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@Autowired
	private Environment env;

	@GetMapping("/listar")
	public List<Producto> listar() {
		return productoService.findAll().stream().map(producto -> {
			producto.setPort(Integer.parseInt(env.getProperty("server.port")));
			return producto;
		}).collect(Collectors.toList());
	}

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) {
		var producto = productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("server.port")));
		return producto;
	}

	@PostMapping("/crear")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto crear(@RequestBody Producto producto) {
		return productoService.save(producto);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Producto editar(@RequestBody Producto producto, @PathVariable Long id) {
		var productoAeditar = productoService.findById(id);
		if (productoAeditar != null) {
			productoAeditar.setNombre(producto.getNombre());
			productoAeditar.setPrecio(producto.getPrecio());
			productoAeditar.setPort(producto.getPort());
			return productoService.save(productoAeditar);
		}
		throw new RuntimeException("No existe el producto");
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		var productoAeliminar = productoService.findById(id);
		if (productoAeliminar != null) {
			productoService.deleteById(id);
		} else {
			throw new RuntimeException("El producto no existe");
		}
	}

}