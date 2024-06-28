package com.prod_mangament_spring.product_manage_spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.prod_mangament_spring.product_manage_spring.product_entity.Product;

@Service
public class ProductService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String addProductService( Product product )
    {
        final String insertQuery = "INSERT INTO Products (ProductName , Price , Stock , Visibility , ProductID) "+
                                    "VALUES ( ? , ? , ? , ? , ? )";


        try {

            jdbcTemplate.update(insertQuery, product.getProductName() , product.getPrice() , product.getStock() , true , product.getId());
            
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            return "Failed to add the data into DB";
        }

        return product.getProductName()+" added to the DB...";
    }

    public Object getProductByIdService( String id )
    {

        Product product = new Product();

        try {

            final String queryGetProduct = "SELECT * FROM products WHERE productid = ?";

            product = jdbcTemplate.queryForObject(queryGetProduct, (rs, rowNum) -> {
                
                Product prod = new Product();
                prod.setId(rs.getString("productid"));
                prod.setProductName(rs.getString("productname"));
                prod.setPrice(rs.getLong("price"));
                prod.setStock(rs.getLong("stock"));
        
                System.out.println(prod.getProductName());
                
                return prod;
            } , id);

            return product;
            
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
            System.err.println("Failed to get product...");
            return "The Product is not found...";
        }
        
    }

    public String updateService(String id , Long value , int option )
    {
        final String queryUpdateStock = "UPDATE products SET stock = ? WHERE productid = ?";
        final String queryGetProductStock = "SELECT stock FROM products WHERE productid = ?";

        switch (option) {

            case 1:
            
                final String queryUpdatePrice = "UPDATE products SET price = ? WHERE productid = ?";
                if( value > 0 )
                {
                    try {
                        jdbcTemplate.update(queryUpdatePrice, value , id);
                        return "Price Updated successfully...";
                    } catch (Exception sqlException) {
                        sqlException.printStackTrace();
                        System.err.println("Failed to update the data...");
                        return "Failed to update the data...";
                    }
                }
                else
                {
                    return "Price is not possible to be negative or zero...";
                }

            case 2:
                
                try{

                    Long actualStock = jdbcTemplate.queryForObject(queryGetProductStock, (rs , rowNum) -> rs.getLong("stock") , id );
                    System.out.println(actualStock);
                        if(value > 0)
                        {
                            jdbcTemplate.update(queryUpdateStock , actualStock +  value , id);
                        }
                        
                    } catch (Exception sqlException) {
                        sqlException.printStackTrace();
                        System.err.println("Failed to update the data...");
                        return "Failed to update the data...";
                    }
            
                    return "Add Stock updated successfully...";
        
            case 3:
                
                try {
                    Long actualStock = jdbcTemplate.queryForObject(queryGetProductStock, (rs ,rowNum) -> rs.getLong("stock") , id);
                    if( actualStock == 0 )
                    {
                        return "Out Of Stock";
                    }
                    else if(value > 0 && actualStock >= value)
                    {
                        jdbcTemplate.update(queryUpdateStock , (actualStock -  value) , id);
                    }
                    else
                    {
                        return "Invalid Stock the value should be between 0 - "+(actualStock+1);
                    }
                    
                } catch (Exception sqlException) {
                    sqlException.printStackTrace();
                    System.err.println("Failed to update the data...");
                    return "Failed to update the data...";
                }
        
                return "Stock updated successfully...";

            default:
                return "Invalid Option";
        }
         
    }

    public String deleteProductService( String id )
    {
        final String queryDeleteProduct = "DELETE FROM products WHERE productid = ?";
        
        try {
            jdbcTemplate.update(queryDeleteProduct, id);
            return "Deleted successfully...";
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        return "The product is not available...";
        }
    }
 
    public ArrayList<Product> requestAllProductsService()
    {
        final String queryGetProductStock = "SELECT * FROM products";
        ArrayList<Product> productList = new ArrayList<>();

        productList = (ArrayList<Product>) jdbcTemplate.query(queryGetProductStock, (rs, rowNum) -> {
            System.out.println("Get into create product...");
            
            Product prod = new Product();
            prod.setId(rs.getString("productid"));
            prod.setProductName(rs.getString("productname"));
            prod.setPrice(rs.getLong("price"));
            prod.setStock(rs.getLong("stock"));
    
            System.out.println(prod.getProductName());
            
            return prod;
        });

        return productList;
    }

    public String updatePurchaseService( ArrayList<Product> products_list )
    {
        String updateQuery = "UPDATE products SET stock = ? WHERE productid = ?";
        for( Product prod : products_list )
        {
            jdbcTemplate.update(updateQuery, prod.getStock() , prod.getId());
        }
        
        return "Inventory Update Successfull....";
    }
    
}