package com.fpt.duantn.services.impl;

import com.fpt.duantn.io.entity.CategoryEntity;
import com.fpt.duantn.io.repository.CategoryRepository;
import com.fpt.duantn.services.CategoryCardService;
import com.fpt.duantn.shrared.dto.CRUD.CategoryDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CategoryCardServiceimpl implements CategoryCardService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getCategoryCard(int page, int limit) {
        List<CategoryDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<CategoryEntity> categoryCardPage = categoryRepository.getCategoryAndBrands(pageableRequest);
        List<CategoryEntity> categoryCart = categoryCardPage.getContent();

        for (CategoryEntity categoryCartEntity : categoryCart) {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(categoryCartEntity, categoryDto);
            returnValue.add(categoryDto);
        }

        return returnValue;
    }
}
