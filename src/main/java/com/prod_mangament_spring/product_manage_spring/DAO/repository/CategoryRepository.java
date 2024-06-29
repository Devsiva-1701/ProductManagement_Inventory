package com.prod_mangament_spring.product_manage_spring.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prod_mangament_spring.product_manage_spring.DAO.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category , Long>{
    
}
