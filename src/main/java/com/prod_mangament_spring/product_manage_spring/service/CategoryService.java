package com.prod_mangament_spring.product_manage_spring.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prod_mangament_spring.product_manage_spring.DAO.entity.Category;
import com.prod_mangament_spring.product_manage_spring.DAO.entity.Product;
import com.prod_mangament_spring.product_manage_spring.DAO.repository.CategoryRepository;
import com.prod_mangament_spring.product_manage_spring.DTO.CategoryDTO;
import com.prod_mangament_spring.product_manage_spring.DTO.ProductUpdateDTO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    StringRedisTemplate redisStringTemplate;

    @CachePut(value = "a" , key = "#categoryDTO.id")
    public String addCategory( CategoryDTO categoryDTO )
    {
        categoryRepository.save( modelMapper.map(categoryDTO, Category.class) );
        return "Category : "+categoryDTO.getName()+" added to categories";
    }

    @Cacheable(value = "a" , key = "#id")
    public String getCategory( Long id )
    {
        System.out.println("\n Fetching from DB...");
        return modelMapper.map(categoryRepository.findById(id).get(), CategoryDTO.class).getName();
    }

    @CacheEvict(value = "a" , key = "#id")
    public String deleteCategory( Long id )
    {
        categoryRepository.deleteById(id);
        return "Category Deleted Successfully...";
    }

    // @Cacheable( value = "prodList" , key = "#categoryId" )
    // public List<ProductUpdateDTO> getProducts( Long categoryId )
    // {
    //     List<Product> productlist = categoryRepository.findById(categoryId).get().getCategoryProducts();
    //     List<ProductUpdateDTO> productDTOlist = new ArrayList<>();

    //     productlist.stream().forEach(product -> {
    //         productDTOlist.add(modelMapper.map(product, ProductUpdateDTO.class ));
    //     });

    //     return productDTOlist;
    // }

    public List<ProductUpdateDTO> getProducts( Long categoryId ) throws JsonMappingException, JsonProcessingException
    {
        String cacheKey = "categoryProducts:"+categoryId;
        String cacheValue = redisStringTemplate.opsForValue().get(cacheKey);

        List<Product> productlist = new ArrayList<>();
        List<ProductUpdateDTO> productDTOlist = new ArrayList<>();


        if(cacheValue != null)
        {
            System.out.println("\nFetching from Cache Memory...\n");
            productDTOlist = objectMapper.readValue(cacheValue, new TypeReference<List<ProductUpdateDTO>>(){});
            return productDTOlist;
        }
        else
        {
            Category category = categoryRepository.findById(categoryId).orElseThrow(()-> 
                new EntityNotFoundException("Not found"));

            productlist = category.getCategoryProducts();

            if (productlist != null) {
                
                for( Product product : productlist )
                {
                    productDTOlist.add(modelMapper.map(product, ProductUpdateDTO.class));
                }
            }
            else
            {
                return null;
            }

            String serializedString = objectMapper.writeValueAsString(productDTOlist);
            redisStringTemplate.opsForValue().set(cacheKey, serializedString);

            return productDTOlist;

        }
    }
    
}
