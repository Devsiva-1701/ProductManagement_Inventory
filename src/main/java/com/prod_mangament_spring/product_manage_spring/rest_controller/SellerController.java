package com.prod_mangament_spring.product_manage_spring.rest_controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prod_mangament_spring.product_manage_spring.DTO.SellerDTO;
import com.prod_mangament_spring.product_manage_spring.service.SellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("seller")
public class SellerController {
    
    @Autowired
    SellerService sellerService;

    @PostMapping("add")
    public String addSeller(@RequestBody SellerDTO sellerDTO) {
        
        return sellerService.addSeller(sellerDTO);
    }
    

}
