package com.prod_mangament_spring.product_manage_spring.rest_controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prod_mangament_spring.product_manage_spring.DTO.ProductCreateDTO;
import com.prod_mangament_spring.product_manage_spring.DTO.ProductUpdateDTO;
import com.prod_mangament_spring.product_manage_spring.service.ProductService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("add")
    public String addProduct(@RequestBody ProductCreateDTO productDTO) {

        return productService.addProductService(productDTO);

    }

    @GetMapping("get/{id}")
    public ProductUpdateDTO getProduct(@PathVariable Long id) {
        return productService.getProductByIdService(id);
    }
    

    @PutMapping("update/{id}")
    public String updatePrice(@RequestBody ProductUpdateDTO productDTO ) {
    
        return productService.updateService( productDTO );

    }

    @DeleteMapping("deleteProduct/{id}")
    public String deleteProduct( @PathVariable Long id ){

        return productService.deleteProductService(id);
        
    }

    @GetMapping("getProducts")
    public ArrayList<ProductUpdateDTO> requestAllProducts() {
        return productService.requestAllProductsService();
    }   
    
    // @PutMapping("updatePurchase")
    // public String updatePurchase(@RequestBody ArrayList<Product> products_list) {
        
    //     return productService.updatePurchaseService(products_list);
    // }
    
    
    
    
}
