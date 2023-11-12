package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;

import java.util.List;

public interface ProductDetailCardService {
    List<ProductDetailDto> getProductDetailCard(int page, int limit);

}
