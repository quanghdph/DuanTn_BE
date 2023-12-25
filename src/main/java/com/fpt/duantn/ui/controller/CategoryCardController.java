package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.CategoryCardService;
import com.fpt.duantn.shrared.dto.CRUD.CategoryDto;
import com.fpt.duantn.ui.model.response.CategoryCardRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/category-list")
public class CategoryCardController {

    @Autowired
    CategoryCardService categoryCardService;

    @GetMapping()
    public List<CategoryCardRest> getCategory(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<CategoryCardRest> returnValue = new ArrayList<>();

        List<CategoryDto> categorys = categoryCardService.getCategoryCard(page, limit);

        for (CategoryDto categoryDto : categorys) {
            CategoryCardRest categoryModel = new CategoryCardRest();
            BeanUtils.copyProperties(categoryDto, categoryModel);
            returnValue.add(categoryModel);
        }

        return returnValue;

    }
}
