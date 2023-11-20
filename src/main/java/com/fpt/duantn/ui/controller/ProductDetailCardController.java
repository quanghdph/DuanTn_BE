package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.ProductDetailCardService;
import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;
import com.fpt.duantn.ui.model.response.ProductDetailCardDetailsRest;
import com.fpt.duantn.ui.model.response.ProductDetailCardRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/product-detail-list")
public class ProductDetailCardController {

    @Autowired
    ProductDetailCardService productDetailCardService;

    @GetMapping()
    public List<ProductDetailCardDetailsRest> getProductDetail(@RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ProductDetailCardDetailsRest> returnValue = new ArrayList<>();

        List<ProductDetailDto> productsDetail = productDetailCardService.getProductDetailCard(page, limit);

        for (ProductDetailDto productDetailDto : productsDetail) {
            ProductDetailCardDetailsRest productDetailModel = new ProductDetailCardDetailsRest();
            BeanUtils.copyProperties(productDetailDto, productDetailModel);
            returnValue.add(productDetailModel);
        }

        return returnValue;

    }
}
