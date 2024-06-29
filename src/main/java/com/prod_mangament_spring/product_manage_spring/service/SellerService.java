package com.prod_mangament_spring.product_manage_spring.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prod_mangament_spring.product_manage_spring.DAO.entity.Seller;
import com.prod_mangament_spring.product_manage_spring.DAO.repository.SellerRepository;
import com.prod_mangament_spring.product_manage_spring.DTO.SellerDTO;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ModelMapper modelMapper;

    public String addSeller( SellerDTO sellerDTO )
    {
        Seller seller = modelMapper.map(sellerDTO, Seller.class );
        sellerRepository.save(seller);
        return "Seller added to the DB"; 
    }

    public String getSellerById( Long Id )
    {
        if( sellerRepository.existsById(Id) )
        {
            return sellerRepository.findById(Id).get().getName();
        }
        else
        {
            return null;
        }
    }

    // public List<SellerDTO> getSe

    public String deleteSeller( Long id )
    {
        if( sellerRepository.existsById(id) )
        {
            sellerRepository.deleteById(id);
            return "Seller deleted successfully...";

        }
        else
        {
            return "Seller not found...";
        }
    }
    
}
