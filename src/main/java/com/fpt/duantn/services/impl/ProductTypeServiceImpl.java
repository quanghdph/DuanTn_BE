package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.ProductTypeServiceException;
import com.fpt.duantn.io.entity.ProductTypeEntity;
import com.fpt.duantn.io.repository.ProductTypeRepository;
import com.fpt.duantn.services.ProductTypeService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.ProductTypeDto;
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
public class ProductTypeServiceImpl implements ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    Utils utils;

    @Override
    public ProductTypeDto createProductType(ProductTypeDto productType) {
        // Kiểm tra xem ProductTypeCode đã tồn tại hay chưa
        if (productTypeRepository.findByProductTypeCode(productType.getProductTypeCode()) != null) {
            throw new ProductTypeServiceException("ProductType with the same code already exists");
        }

        // Chuyển đổi ProductTypeDto thành ProductTypeEntity
        ModelMapper modelMapper = new ModelMapper();
        ProductTypeEntity productTypeEntity = modelMapper.map(productType, ProductTypeEntity.class);

        // Tạo một mã ngẫu nhiên cho ProductTypeCode (tùy theo yêu cầu)
        String publicProductTypeCode = utils.generateColorCode(8);
        productTypeEntity.setProductTypeCode(publicProductTypeCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        ProductTypeEntity storedProductTypeDetails = productTypeRepository.save(productTypeEntity);

        // Chuyển đổi ProductTypeEntity thành ProductTypeDto
        ProductTypeDto returnValue = modelMapper.map(storedProductTypeDetails, ProductTypeDto.class);

        return returnValue;
    }



    @Override
    public ProductTypeDto getProductTypeByProductTypeCode(String productTypeCode) {
        ProductTypeDto returnValue = new ProductTypeDto();
        ProductTypeEntity productTypeEntity = productTypeRepository.findByProductTypeCode(productTypeCode);

        if (productTypeEntity == null)
            throw new ProductTypeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(productTypeEntity, returnValue);

        return returnValue;
    }

    @Override
    public ProductTypeDto updateProductType(String productTypeCode, ProductTypeDto productType) {
        ProductTypeDto returnValue = new ProductTypeDto();

        ProductTypeEntity productTypeEntity = productTypeRepository.findByProductTypeCode(productTypeCode);

        if (productTypeEntity == null)
            throw new ProductTypeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        productTypeEntity.setProductTypeName(productType.getProductTypeName());

        ProductTypeEntity updatedProductTypes = productTypeRepository.save(productTypeEntity);
        returnValue = new ModelMapper().map(updatedProductTypes, ProductTypeDto.class);

        return returnValue;
    }

    @Override
    public void deleteProductType(String productTypeCode) {
        ProductTypeEntity productTypeEntity = productTypeRepository.findByProductTypeCode(productTypeCode);

        if (productTypeEntity == null)
            throw new ProductTypeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        productTypeRepository.delete(productTypeEntity);
    }

    @Override
    public List<ProductTypeDto> getProductTypes(int page, int limit) {
        List<ProductTypeDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<ProductTypeEntity> productTypePage = productTypeRepository.findAll(pageableRequest);
        List<ProductTypeEntity> productTypes = productTypePage.getContent();

        for (ProductTypeEntity productTypeEntity : productTypes) {
            ProductTypeDto productTypeDto = new ProductTypeDto();
            BeanUtils.copyProperties(productTypeEntity, productTypeDto);
            returnValue.add(productTypeDto);
        }

        return returnValue;
    }

    @Override
    public List<ProductTypeDto> getProductTypeByProductTypeName(String productTypeName, int page, int limit) {
        List<ProductTypeDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ProductTypeEntity> productTypePage = productTypeRepository.findByProductTypeNameContainingOrderByIdAsc(productTypeName, pageableRequest);
        List<ProductTypeEntity> productTypes = productTypePage.getContent();

        for (ProductTypeEntity productTypeEntity : productTypes) {
            ProductTypeDto productTypeDto = new ProductTypeDto();
            BeanUtils.copyProperties(productTypeEntity, productTypeDto);
            returnValue.add(productTypeDto);
        }

        return returnValue;
    }
}
