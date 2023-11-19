package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto category);
    CategoryDto getCategoryById(Long categoryId);
    CategoryDto updateCategory(Long categoryId, CategoryDto category);
    void deleteCategory(Long categoryId);
    List<CategoryDto> getCategorys(int page, int limit, String filter);

    Long count(String filter);
    List<CategoryDto> getCategoryByCategoryName(String categoryName, int page, int limit);


}
