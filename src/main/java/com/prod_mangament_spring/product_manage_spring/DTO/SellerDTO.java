package com.prod_mangament_spring.product_manage_spring.DTO;

import java.util.List;

import com.prod_mangament_spring.product_manage_spring.DAO.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerDTO {

    private String name;
    private String email;
    private List<Product> sellerProducts;  
}
