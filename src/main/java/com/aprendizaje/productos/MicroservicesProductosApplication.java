package com.aprendizaje.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
// Hacemos lo siguiente para que Spring boot detecte la entidad Producto, ya que ya no está
//dentro del package base de este proyecto
@EntityScan({ "com.aprendizaje.microservices.commons.models.entities" })
public class MicroservicesProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesProductosApplication.class, args);
	}

}
