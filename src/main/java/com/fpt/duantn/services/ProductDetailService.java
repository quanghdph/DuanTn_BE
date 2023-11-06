package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;

import java.util.List;

public interface ProductDetailService {

    ProductDetailDto createProductDetail(ProductDetailDto productDetail);
    ProductDetailDto getProductDetailByProductDetailCode(Long productDetailCode);
    ProductDetailDto updateProductDetail(Long productDetailCode, ProductDetailDto productDetail);
    void deleteProductDetail(Long productDetailId);
    List<ProductDetailDto> getProductDetails(int page, int limit);
//    List<ProductDetailDto> getProductDetailByProductDetailName(String productDetailName, int page, int limit);


}
