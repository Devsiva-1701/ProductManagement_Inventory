package com.prod_mangament_spring.product_manage_spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import com.prod_mangament_spring.product_manage_spring.DAO.entity.Product;
import com.prod_mangament_spring.product_manage_spring.DAO.repository.ProductRepository;
import com.prod_mangament_spring.product_manage_spring.DTO.ProductCreateDTO;
import com.prod_mangament_spring.product_manage_spring.DTO.ProductUpdateDTO;


@Service
public class ProductService {

    // @Autowired
    // private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;


    public String addProductService( ProductCreateDTO productDTO )
    {

        if( productDTO.getPrice() > 0 )
        {
            if( productDTO.getStock() >= 0 )
            {
                Product product = modelMapper.map( productDTO , Product.class );
                product.getCategory().setName(categoryService.getCategory(productDTO.getCategory().getId()));
                product.getSeller().setName(sellerService.getSellerById(productDTO.getSeller().getId()));
                product.setCategoryName(categoryService.getCategory(productDTO.getCategory().getId()));
                product.setSellerName(sellerService.getSellerById(productDTO.getSeller().getId()));
                productRepository.save(product);
                return product.getProductName()+" added to the DB...";
            }
            else
            {
                return "invalid Stock...";
            }
        }
        else
        {
            return "Invalid Price...";
        }

        
    }

    public ProductUpdateDTO getProductByIdService( Long id )
    {

        if( productRepository.existsById( id ) )
        {
            ProductUpdateDTO productDTO = modelMapper.map(productRepository.findById(id), ProductUpdateDTO.class);
            return productDTO;
        }
        else
        {
            return null;
        }
        
    }

    public String updateService(ProductUpdateDTO productDTO)
    {
        
        if( productRepository.existsById(productDTO.getId()))
        {
            ProductUpdateDTO oldProductDTO = modelMapper.map(productRepository.findById(productDTO.getId()), ProductUpdateDTO.class); 
            if( oldProductDTO.getId() == productDTO.getId() )
            {
                if( productDTO.getPrice() > 0 )
                {
                    if( productDTO.getStock() >= 0 )
                    {
                        productRepository.save(modelMapper.map(productDTO , Product.class));
                        return "Update successfully...";
                    }
                    else
                    {
                        return "Invalid Stock...";
                    }
                }
                else
                {
                    return "Invalid Price...";
                }
            
        }
        else
        {
            return "Product not found...";
        }
        
         
    }
    else
    {
        return "Product Not found...";
    }
}

    public String deleteProductService( Long id )
    {
        if( productRepository.existsById(id) )
        {
            productRepository.deleteById(id);
            return "Product deleted successfully...";
        }
        else
        {
            return "Product not found...";
        }
    }
 
    public ArrayList<ProductUpdateDTO> requestAllProductsService()
    {
        ArrayList<ProductUpdateDTO> productDTOlist = new ArrayList<>();

        ArrayList<Product> productList = (ArrayList<Product>) productRepository.findAll();

        productList.stream().forEach(product -> {
            productDTOlist.add(modelMapper.map(product, ProductUpdateDTO.class));
        });

        return productDTOlist;
    }

    // public String updatePurchaseService( ArrayList<Product> products_list )
    // {
    //     String updateQuery = "UPDATE products SET stock = ? WHERE productid = ?";
    //     for( Product prod : products_list )
    //     {
    //         jdbcTemplate.update(updateQuery, prod.getStock() , prod.getId());
    //     }
        
    //     return "Inventory Update Successfull....";
    // }
    
}