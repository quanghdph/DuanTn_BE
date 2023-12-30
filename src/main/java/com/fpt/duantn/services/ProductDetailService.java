package com.fpt.duantn.services;

import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDetailService {

    ProductDetailDto createProductDetail(ProductDetailDto productDetail);
    ProductDetailDto getProductDetailById(Long productDetailId);

    Optional<ProductDetailEntity> findById(Long aLong);

    ProductDetailDto updateProductDetail(Long productDetailId, ProductDetailDto productDetail);
    void deleteProductDetail(Long productDetailId);
    List<ProductDetailDto> getProductsDetail(int page, int limit, String filter);
    Long count(String filter);
    ProductDetailDto getProductDetailsByProductAndColorAndSize(Long productId,Long colorId, Long sizeId);
    Optional<Double> sumMoneyByBillIdAndType(Long id, Integer type);

    <S extends ProductDetailEntity> List<S> saveAll(Iterable<S> entities);

    <S extends ProductDetailEntity> S save(S entity);
}
