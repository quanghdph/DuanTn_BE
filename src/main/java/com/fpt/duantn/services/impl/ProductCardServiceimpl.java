package com.fpt.duantn.services.impl;


import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.repository.ProductRepository;
import com.fpt.duantn.services.ProductCardService;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCardServiceimpl implements ProductCardService {

    @Autowired
    ProductRepository productRepository;


//    @Override
//    public List<ProductDto> getProductCard(String productName, int page, int limit) {
//        List<ProductDto> returnValue = new ArrayList<>();
//
//        if(page>0) page = page-1;
//
//        Pageable pageableRequest = PageRequest.of(page, limit);
//        Page<ProductEntity> productCardPage = productRepository.getProductsAndColors(productName, pageableRequest);
//        List<ProductEntity> productCart = productCardPage.getContent();
//
//        for (ProductEntity productCartEntity : productCart) {
//            ProductDto colorDto = new ProductDto();
//            BeanUtils.copyProperties(productCartEntity, colorDto);
//            returnValue.add(colorDto);
//        }
//
//        return returnValue;
//    }

    @Override
    public List<ProductDto> getProductCardByproductName(String productName, int page, int limit) {
        return null;
    }

    @Override
    public List<ProductDto> getProductCard(int page, int limit) {
        List<ProductDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ProductEntity> productCardPage = productRepository.getProductsAndColors( pageableRequest);
        List<ProductEntity> productCart = productCardPage.getContent();

        for (ProductEntity productCartEntity : productCart) {
            ProductDto colorDto = new ProductDto();
            BeanUtils.copyProperties(productCartEntity, colorDto);
            returnValue.add(colorDto);
        }

        return returnValue;
    }




}
