package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.CategoryService;
import com.fpt.duantn.shrared.dto.CRUD.CategoryDto;
import com.fpt.duantn.ui.model.request.CategoryRequest;
import com.fpt.duantn.ui.model.response.CategoryRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/category")
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
    public CategoryRest createCategory(@RequestBody CategoryRequest categoryDetails) throws Exception {
        CategoryRest returnValue = new CategoryRest();

        ModelMapper modelMapper = new ModelMapper();
        CategoryDto categoryDto = modelMapper.map(categoryDetails, CategoryDto.class);

        categoryDto.setProductType(categoryDetails.getProductType());

        CategoryDto createdUser = categoryService.createCategory(categoryDto);
        returnValue = modelMapper.map(createdUser, CategoryRest.class);

        return returnValue;
    }



    @PutMapping(path = "/{id}")
    public CategoryRest updateCategory(@PathVariable String id, @RequestBody CategoryRequest categoryDetails) {
        CategoryRest returnValue = new CategoryRest();

        CategoryDto categoryDto = new CategoryDto();
        categoryDto = new ModelMapper().map(categoryDetails, CategoryDto.class);

        categoryDto.setProductType(categoryDetails.getProductType());
        try {
        CategoryDto updateCategory = categoryService.updateCategory(id, categoryDto);
        returnValue = new ModelMapper().map(updateCategory, CategoryRest.class);
        }catch (Exception e){
            System.out.println(e);
        }
        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteCategory(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            categoryService.deleteCategory(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Loai sản phẩm: Loai sản phẩm có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Loai sản phẩm: " + e.getMessage());
        }
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

    @GetMapping()
    public PaginationRest getCategorys(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "5") int limit,
                                       @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<CategoryRest> returnValue = new ArrayList<>();

        List<CategoryDto> categorys = categoryService.getCategorys(page, limit, filter);

        for (CategoryDto categoryDto : categorys) {
            CategoryRest categoryModel = new CategoryRest();
            BeanUtils.copyProperties(categoryDto, categoryModel);
            returnValue.add(categoryModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListCategories(returnValue);
        paginationRest.setTotal(categoryService.count(filter));

        return paginationRest;
    }


}
