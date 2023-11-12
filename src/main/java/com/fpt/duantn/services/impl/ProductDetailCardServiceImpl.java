package com.fpt.duantn.services.impl;

import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.repository.ProductDetailRepository;
import com.fpt.duantn.services.ProductDetailCardService;
import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;
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
public class ProductDetailCardServiceImpl implements ProductDetailCardService {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDetailDto> getProductDetailCard(int page, int limit) {
        List<ProductDetailDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ProductDetailEntity> productDetailPage = productDetailRepository.getProductsDetailAndColors(pageableRequest);
        List<ProductDetailEntity> productDetail = productDetailPage.getContent();

        for (ProductDetailEntity productDetailEntity : productDetail) {
            ProductDetailDto productDetailDto = new ProductDetailDto();
            BeanUtils.copyProperties(productDetailEntity, productDetailDto);
            returnValue.add(productDetailDto);
        }

        return returnValue;
    }
}
