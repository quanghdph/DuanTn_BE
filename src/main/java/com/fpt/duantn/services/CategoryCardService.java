package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CategoryDto;

import java.util.List;

public interface CategoryCardService {

    List<CategoryDto> getCategoryCard(int page, int limit);


}
