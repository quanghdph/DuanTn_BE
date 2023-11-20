package com.fpt.duantn.ui.controller;


import com.fpt.duantn.services.ProductCardService;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;
import com.fpt.duantn.ui.model.response.ProductDetailCardRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product-list")
public class ProductCardController {

    @Autowired
    ProductCardService productCardService;


    @GetMapping()
    public List<ProductDetailCardRest> getProduct(@RequestParam(value = "page", defaultValue = "0") int page,
                                                  @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ProductDetailCardRest> returnValue = new ArrayList<>();

        List<ProductDto> products = productCardService.getProductCard(page, limit);

        for (ProductDto productDto : products) {
            ProductDetailCardRest productModel = new ProductDetailCardRest();
            BeanUtils.copyProperties(productDto, productModel);
            returnValue.add(productModel);
        }

        return returnValue;

    }




}
