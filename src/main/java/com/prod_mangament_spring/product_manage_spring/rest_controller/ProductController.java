package com.prod_mangament_spring.product_manage_spring.rest_controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prod_mangament_spring.product_manage_spring.product_entity.Product;
import com.prod_mangament_spring.product_manage_spring.service.ProductService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public String addProduct(@RequestBody Product product) {

        return productService.addProductService(product);

    }

    @GetMapping("get/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable String id) {
        Object result =  productService.getProductByIdService(id);

        if(result instanceof Product)
        {
            return ResponseEntity.ok().body((Product) result);
        }
        else
        {
            return ResponseEntity.ok().body((String) result);
        }
    }
    

    @PutMapping("update/{id}")
    public String updatePrice(@RequestParam(value = "option") int option , @RequestParam(value = "value") Long value , @PathVariable String id) {
    
        return productService.updateService(id, value , option);

    }

    @DeleteMapping("deleteProduct/{id}")
    public String deleteProduct( @PathVariable String id ){

        return productService.deleteProductService(id);
        
    }

    @GetMapping("getProducts")
    public ArrayList<Product> requestAllProducts() {
        return productService.requestAllProductsService();
    }   
    
    @PutMapping("updatePurchase")
    public String updatePurchase(@RequestBody ArrayList<Product> products_list) {
        
        return productService.updatePurchaseService(products_list);
    }
    
    
    
    
}
