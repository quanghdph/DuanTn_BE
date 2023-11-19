package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.BrandDto;

import java.util.List;

public interface BrandService {

    BrandDto createBrand(BrandDto brand);
    BrandDto getBrandById(Long brandId);
    BrandDto updateBrand(Long brandId, BrandDto brand);
    void deleteBrand(Long brandId);
    List<BrandDto> getBrands(int page, int limit, String filter);
    Long count(String filter);

    List<BrandDto> getBrandByBrandName(String brandName, int page, int limit);


}
