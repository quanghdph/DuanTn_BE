package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto category);
    CategoryDto getCategoryByCategoryCode(String categoryCode);
    CategoryDto updateCategory(String categoryCode, CategoryDto category);
    void deleteCategory(String categoryCode);
    List<CategoryDto> getCategorys(int page, int limit);
    List<CategoryDto> getCategoryByCategoryName(String categoryName, int page, int limit);


}
