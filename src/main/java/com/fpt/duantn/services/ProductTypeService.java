package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ProductTypeDto;

import java.util.List;

public interface ProductTypeService {

    ProductTypeDto createProductType(ProductTypeDto productType);
    ProductTypeDto getProductTypeByProductTypeCode(String productTypeCode);
    ProductTypeDto updateProductType(String productTypeCode, ProductTypeDto productType);
    void deleteProductType(String productTypeCode);
    List<ProductTypeDto> getProductTypes(int page, int limit);
    List<ProductTypeDto> getProductTypeByProductTypeName(String productTypeName, int page, int limit);


}
