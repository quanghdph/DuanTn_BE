package com.fpt.duantn.services;

import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDto createProduct(ProductDto product);
    ProductDto getProductById(Long productId);

    Optional<ProductEntity> findById(Long aLong);

    ProductDto updateProduct(Long productId, ProductDto product);
    void deleteProduct(Long productId);
    List<ProductDto> getProducts(int page, int limit, String filter);
    Long count(String filter);

    Long count();

    List<ProductDto> getProductByProductName(String productName, int page, int limit);

}
