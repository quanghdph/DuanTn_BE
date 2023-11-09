package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.CategoryService;
import com.fpt.duantn.shrared.dto.CRUD.CategoryDto;
import com.fpt.duantn.ui.model.request.CategoryDetailsRequestModel;
import com.fpt.duantn.ui.model.response.CategoryRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(path = "/{id}")
    public CategoryRest getCategory(@PathVariable String id) {
        CategoryRest returnValue = new CategoryRest();

        CategoryDto categoryDto = categoryService.getCategoryByCategoryCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(categoryDto, CategoryRest.class);

        return returnValue;
    }

    @PostMapping()
    public CategoryRest createCategory(@RequestBody CategoryDetailsRequestModel categoryDetails) throws Exception {
        CategoryRest returnValue = new CategoryRest();

        ModelMapper modelMapper = new ModelMapper();
        CategoryDto categoryDto = modelMapper.map(categoryDetails, CategoryDto.class);

        categoryDto.setProductType(categoryDetails.getProductType());

        CategoryDto createdUser = categoryService.createCategory(categoryDto);
        returnValue = modelMapper.map(createdUser, CategoryRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<CategoryRest> getCategorys(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<CategoryRest> returnValue = new ArrayList<>();

        List<CategoryDto> categorys = categoryService.getCategorys(page, limit);

        for (CategoryDto categoryDto : categorys) {
            CategoryRest categoryModel = new CategoryRest();
            BeanUtils.copyProperties(categoryDto, categoryModel);
            returnValue.add(categoryModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public CategoryRest updateCategory(@PathVariable String id, @RequestBody CategoryDetailsRequestModel categoryDetails) {
        CategoryRest returnValue = new CategoryRest();

        CategoryDto categoryDto = new CategoryDto();
        categoryDto = new ModelMapper().map(categoryDetails, CategoryDto.class);

        categoryDto.setProductType(categoryDetails.getProductType());

        CategoryDto updateCategory = categoryService.updateCategory(id, categoryDto);
        returnValue = new ModelMapper().map(updateCategory, CategoryRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteCategory(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        categoryService.deleteCategory(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<CategoryRest> searchCategorys(@RequestParam(value = "categoryName") String categoryName,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<CategoryRest> returnValue = new ArrayList<>();

        List<CategoryDto> categorys = categoryService.getCategoryByCategoryName(categoryName, page, limit);

        for (CategoryDto categoryDto : categorys) {
            CategoryRest categoryModel = new CategoryRest();
            BeanUtils.copyProperties(categoryDto, categoryModel);
            returnValue.add(categoryModel);
        }

        return returnValue;
    }

}
