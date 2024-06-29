package com.prod_mangament_spring.product_manage_spring.DTO;

import com.prod_mangament_spring.product_manage_spring.DAO.entity.Category;
import com.prod_mangament_spring.product_manage_spring.DAO.entity.Seller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateDTO {

    private Long id;
    private String productName;
    private Long price;
    private Long stock;
    private Seller seller;
    private Category category;
    
}
