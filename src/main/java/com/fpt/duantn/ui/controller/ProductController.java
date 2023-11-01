package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.ProductService;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;
import com.fpt.duantn.ui.model.request.ProductDetailsRequestModel;
import com.fpt.duantn.ui.model.response.ProductRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(path = "/{id}")
    public ProductRest getProduct(@PathVariable String id) {
        ProductRest returnValue = new ProductRest();

        ProductDto productDto = productService.getProductByProductCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(productDto, ProductRest.class);

        return returnValue;
    }

    @PostMapping()
    public ProductRest createProduct(@RequestBody ProductDetailsRequestModel productDetails) throws Exception {
        ProductRest returnValue = new ProductRest();

        ModelMapper modelMapper = new ModelMapper();
        ProductDto productDto = modelMapper.map(productDetails, ProductDto.class);

        productDto.setCategory(productDetails.getCategory());
        productDto.setBrand(productDetails.getBrand());

        ProductDto createdUser = productService.createProduct(productDto);
        returnValue = modelMapper.map(createdUser, ProductRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<ProductRest> getProducts(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ProductRest> returnValue = new ArrayList<>();

        List<ProductDto> products = productService.getProducts(page, limit);

        for (ProductDto productDto : products) {
            ProductRest productModel = new ProductRest();
            BeanUtils.copyProperties(productDto, productModel);
            returnValue.add(productModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public ProductRest updateProduct(@PathVariable String id, @RequestBody ProductDetailsRequestModel productDetails) {
        ProductRest returnValue = new ProductRest();

        ProductDto productDto = new ProductDto();
        productDto = new ModelMapper().map(productDetails, ProductDto.class);

        productDto.setCategory(productDetails.getCategory());
        productDto.setBrand(productDetails.getBrand());

        ProductDto updateProduct = productService.updateProduct(id, productDto);
        returnValue = new ModelMapper().map(updateProduct, ProductRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteProduct(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        productService.deleteProduct(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<ProductRest> searchProducts(@RequestParam(value = "productName") String productName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ProductRest> returnValue = new ArrayList<>();

        List<ProductDto> products = productService.getProductByProductName(productName, page, limit);

        for (ProductDto productDto : products) {
            ProductRest productModel = new ProductRest();
            BeanUtils.copyProperties(productDto, productModel);
            returnValue.add(productModel);
        }

        return returnValue;
    }

}
