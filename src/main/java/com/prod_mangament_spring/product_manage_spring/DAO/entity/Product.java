package com.prod_mangament_spring.product_manage_spring.DAO.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String productName;
    private Long price;
    private Long stock;
    @ManyToOne
    @JsonBackReference("seller")
    private Seller seller;
    private String sellerName;
    @ManyToOne
    @JsonBackReference("category")
    private Category category;
    private String categoryName;
    
    
}
