package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.ProductTypeService;
import com.fpt.duantn.shrared.dto.CRUD.ProductTypeDto;
import com.fpt.duantn.ui.model.request.ProductTypeDetailsRequestModel;
import com.fpt.duantn.ui.model.response.ProductTypeRest;
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
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    @GetMapping(path = "/{id}")
    public ProductTypeRest getProductType(@PathVariable String id) {
        ProductTypeRest returnValue = new ProductTypeRest();

        ProductTypeDto productTypeDto = productTypeService.getProductTypeByProductTypeCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(productTypeDto, ProductTypeRest.class);

        return returnValue;
    }

    @PostMapping()
    public ProductTypeRest createProductType(@RequestBody ProductTypeDetailsRequestModel productTypeDetails) throws Exception {
        ProductTypeRest returnValue = new ProductTypeRest();

        ModelMapper modelMapper = new ModelMapper();
        ProductTypeDto productTypeDto = modelMapper.map(productTypeDetails, ProductTypeDto.class);

        ProductTypeDto createdUser = productTypeService.createProductType(productTypeDto);
        returnValue = modelMapper.map(createdUser, ProductTypeRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<ProductTypeRest> getProductTypes(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ProductTypeRest> returnValue = new ArrayList<>();

        List<ProductTypeDto> productTypes = productTypeService.getProductTypes(page, limit);

        for (ProductTypeDto productTypeDto : productTypes) {
            ProductTypeRest productTypeModel = new ProductTypeRest();
            BeanUtils.copyProperties(productTypeDto, productTypeModel);
            returnValue.add(productTypeModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public ProductTypeRest updateProductType(@PathVariable String id, @RequestBody ProductTypeDetailsRequestModel productTypeDetails) {
        ProductTypeRest returnValue = new ProductTypeRest();

        ProductTypeDto productTypeDto = new ProductTypeDto();
        productTypeDto = new ModelMapper().map(productTypeDetails, ProductTypeDto.class);

        ProductTypeDto updateProductType = productTypeService.updateProductType(id, productTypeDto);
        returnValue = new ModelMapper().map(updateProductType, ProductTypeRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteProductType(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        productTypeService.deleteProductType(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<ProductTypeRest> searchProductTypes(@RequestParam(value = "productTypeName") String productTypeName,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<ProductTypeRest> returnValue = new ArrayList<>();

        List<ProductTypeDto> productTypes = productTypeService.getProductTypeByProductTypeName(productTypeName, page, limit);

        for (ProductTypeDto productTypeDto : productTypes) {
            ProductTypeRest productTypeModel = new ProductTypeRest();
            BeanUtils.copyProperties(productTypeDto, productTypeModel);
            returnValue.add(productTypeModel);
        }

        return returnValue;
    }

}
