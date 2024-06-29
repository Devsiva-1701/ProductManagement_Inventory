package com.prod_mangament_spring.product_manage_spring.rest_controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.prod_mangament_spring.product_manage_spring.DTO.CategoryDTO;
import com.prod_mangament_spring.product_manage_spring.DTO.ProductUpdateDTO;
import com.prod_mangament_spring.product_manage_spring.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("add")
    public String addCategory(@RequestBody CategoryDTO categoryDTO) {
        
        return categoryService.addCategory(categoryDTO);
    }
    @GetMapping("get/{id}")
    public String get(@PathVariable Long id) {
        return categoryService.getCategory(id);
    }

    @GetMapping("getProducts/{id}")
    public List<ProductUpdateDTO> getProducts(@PathVariable Long id) throws JsonMappingException, JsonProcessingException {
        return categoryService.getProducts(id);
    }
    
    
    
    
}
