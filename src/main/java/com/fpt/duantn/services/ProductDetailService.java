package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;

import java.util.List;

public interface ProductDetailService {

    ProductDetailDto createProductDetail(ProductDetailDto productDetail);
    ProductDetailDto getProductDetailByProductDetailCode(Long productDetailId);
    ProductDetailDto updateProductDetail(Long productDetailId, ProductDetailDto productDetail);
    void deleteProductDetail(Long productDetailId);
    List<ProductDetailDto> getProductsDetail(int page, int limit, String filter);
    Long count(String filter);
//    List<ProductDetailDto> getProductByProductDetailCode(String productDetailCode, int page, int limit);





}
