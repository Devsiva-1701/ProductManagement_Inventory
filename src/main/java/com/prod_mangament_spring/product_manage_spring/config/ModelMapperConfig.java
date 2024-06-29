package com.prod_mangament_spring.product_manage_spring.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
    
}
