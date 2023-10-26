package com.fpt.duantn.services;



import com.fpt.duantn.shrared.dto.CRUD.ProductDto;

import java.util.List;

public interface ProductCardService {
    List<ProductDto> getProductCardByproductName(String productName, int page, int limit);

    List<ProductDto> getProductCard( int page, int limit);

}
