package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;


import java.util.List;

public interface ProductDetailService {

    ProductDetailDto createProductDetail(ProductDetailDto productDetail);
    ProductDetailDto getProductDetailByProductDetailCode(Long productDetailId);
    ProductDetailDto updateProductDetail(Long productDetailId, ProductDetailDto productDetail);
    void deleteProductDetail(Long productDetailId);
    List<ProductDetailDto> getProductsDetail(int page, int limit, String filter);
    Long count(String filter);
    ProductDetailDto getProductDetailsByProductAndColorAndSize(Long productId,Long colorId, Long sizeId);

}
