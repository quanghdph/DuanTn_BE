package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.CategoryServiceException;
import com.fpt.duantn.io.entity.CategoryEntity;
import com.fpt.duantn.io.repository.CategoryRepository;
import com.fpt.duantn.services.CategoryService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.CategoryDto;
import com.fpt.duantn.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    Utils utils;

    @Override
    public CategoryDto createCategory(CategoryDto category) {
        // Kiểm tra xem CategoryCode đã tồn tại hay chưa
        if (categoryRepository.findByCategoryCode(category.getCategoryCode()) != null) {
            throw new CategoryServiceException("Category with the same code already exists");
        }

        // Chuyển đổi CategoryDto thành CategoryEntity
        ModelMapper modelMapper = new ModelMapper();
        CategoryEntity categoryEntity = modelMapper.map(category, CategoryEntity.class);

        // Tạo một mã ngẫu nhiên cho CategoryCode (tùy theo yêu cầu)
        String publicCategoryCode = utils.generateColorCode(8);
        categoryEntity.setCategoryCode(publicCategoryCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        CategoryEntity storedCategoryDetails = categoryRepository.save(categoryEntity);

        // Chuyển đổi CategoryEntity thành CategoryDto
        CategoryDto returnValue = modelMapper.map(storedCategoryDetails, CategoryDto.class);

        return returnValue;
    }



    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        CategoryDto returnValue = new CategoryDto();
        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityById(categoryId);

        if (categoryEntity == null)
            throw new CategoryServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(categoryEntity, returnValue);

        return returnValue;
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto category) {
        CategoryDto returnValue = new CategoryDto();

        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityById(categoryId);

        if (categoryEntity == null)
            throw new CategoryServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        categoryEntity.setCategoryName(category.getCategoryName());
        categoryEntity.setStatus(category.getStatus());
        categoryEntity.setUpdateDate(category.getUpdateDate());
        categoryEntity.setCreateDate(category.getCreateDate());

        CategoryEntity updatedCategorys = categoryRepository.save(categoryEntity);
        returnValue = new ModelMapper().map(updatedCategorys, CategoryDto.class);

        return returnValue;
    }

    @Override
    public void deleteCategory(Long categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityById(categoryId);

        if (categoryEntity == null)
            throw new CategoryServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        categoryRepository.delete(categoryEntity);
    }

    @Override
    public List<CategoryDto> getCategorys(int page, int limit,String filter) {
        List<CategoryDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<CategoryEntity> categoryPage = categoryRepository.filter(filter, pageableRequest);
        List<CategoryEntity> categorys = categoryPage.getContent();

        for (CategoryEntity categoryEntity : categorys) {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(categoryEntity, categoryDto);
            returnValue.add(categoryDto);
        }

        return returnValue;
    }

    @Override
    public Long count(String filter) {

        Long total = categoryRepository.count(filter);
        return total;
    }

    @Override
    public List<CategoryDto> getCategoryByCategoryName(String categoryName, int page, int limit) {
        List<CategoryDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<CategoryEntity> categoryPage = categoryRepository.findByCategoryNameContainingOrderByIdAsc(categoryName, pageableRequest);
        List<CategoryEntity> categorys = categoryPage.getContent();

        for (CategoryEntity categoryEntity : categorys) {
            CategoryDto categoryDto = new CategoryDto();
            BeanUtils.copyProperties(categoryEntity, categoryDto);
            returnValue.add(categoryDto);
        }

        return returnValue;
    }
}
