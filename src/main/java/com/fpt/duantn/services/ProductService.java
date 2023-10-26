package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto createProduct(ProductDto product);
    ProductDto getProductByProductCode(String productCode);
    ProductDto updateProduct(String productCode, ProductDto product);
    void deleteProduct(String productCode);
    List<ProductDto> getProducts(int page, int limit);
    List<ProductDto> getProductByProductName(String productName, int page, int limit);


}
