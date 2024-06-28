package com.prod_mangament_spring.product_manage_spring.product_entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    private String productName;
    private Long price;
    private Long stock;

    
}
