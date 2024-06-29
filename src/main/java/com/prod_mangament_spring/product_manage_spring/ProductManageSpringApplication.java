package com.prod_mangament_spring.product_manage_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProductManageSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManageSpringApplication.class, args);
	}

}
