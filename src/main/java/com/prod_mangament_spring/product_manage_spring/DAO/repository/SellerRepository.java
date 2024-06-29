package com.prod_mangament_spring.product_manage_spring.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prod_mangament_spring.product_manage_spring.DAO.entity.Seller;

@Repository
public interface SellerRepository extends JpaRepository<Seller , Long >  {
    
}
