package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;

import java.util.List;

public interface ProductDetailService {

    ProductDetailDto createProductDetail(ProductDetailDto productDetail);
    ProductDetailDto getProductDetailByProductDetailCode(String productDetailCode);
    ProductDetailDto updateProductDetail(String productDetailCode, ProductDetailDto productDetail);
    void deleteProductDetail(String productDetailCode);
    List<ProductDetailDto> getProductDetails(int page, int limit);
    List<ProductDetailDto> getProductDetailByProductDetailName(String productDetailName, int page, int limit);


}
