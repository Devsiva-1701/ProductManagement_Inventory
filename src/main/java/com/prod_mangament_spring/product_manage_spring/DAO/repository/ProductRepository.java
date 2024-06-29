package com.prod_mangament_spring.product_manage_spring.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prod_mangament_spring.product_manage_spring.DAO.entity.Product;

public interface ProductRepository extends JpaRepository<Product , Long> {
    
}
