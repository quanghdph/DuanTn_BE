package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.ProductServiceException;
import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.repository.ProductRepository;
import com.fpt.duantn.services.ProductService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.ProductDto;
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
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    Utils utils;

    @Override
    public ProductDto createProduct(ProductDto product) {
        // Kiểm tra xem ProductCode đã tồn tại hay chưa
        if (productRepository.findByProductCode(product.getProductCode()) != null) {
            throw new ProductServiceException("Product with the same code already exists");
        }

        // Chuyển đổi ProductDto thành ProductEntity
        ModelMapper modelMapper = new ModelMapper();
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);

        // Tạo một mã ngẫu nhiên cho ProductCode (tùy theo yêu cầu)
        String publicProductCode = utils.generateColorCode(8);
        productEntity.setProductCode(publicProductCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        ProductEntity storedProductDetails = productRepository.save(productEntity);

        // Chuyển đổi ProductEntity thành ProductDto
        ProductDto returnValue = modelMapper.map(storedProductDetails, ProductDto.class);

        return returnValue;
    }



    @Override
    public ProductDto getProductByProductCode(String productCode) {
        ProductDto returnValue = new ProductDto();
        ProductEntity productEntity = productRepository.findByProductCode(productCode);

        if (productEntity == null)
            throw new ProductServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(productEntity, returnValue);

        return returnValue;
    }

    @Override
    public ProductDto updateProduct(String productCode, ProductDto product) {
        ProductDto returnValue = new ProductDto();

        ProductEntity productEntity = productRepository.findByProductCode(productCode);

        if (productEntity == null)
            throw new ProductServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        productEntity.setProductName(product.getProductName());
        productEntity.setCategory(product.getCategory());
        productEntity.setBrand(product.getBrand());
        productEntity.setMainImage(product.getMainImage());
        productEntity.setDescription(product.getDescription());
        productEntity.setProductCode(product.getProductCode());
        productEntity.setQuantity(product.getQuantity());
        productEntity.setPrice(product.getPrice());
        productEntity.setStatus(product.getStatus());
        productEntity.setUpdateDate(product.getUpdateDate());
        productEntity.setCreateDate(product.getCreateDate());

        ProductEntity updatedProducts = productRepository.save(productEntity);
        returnValue = new ModelMapper().map(updatedProducts, ProductDto.class);

        return returnValue;
    }

    @Override
    public void deleteProduct(String productCode) {
        ProductEntity productEntity = productRepository.findByProductCode(productCode);

        if (productEntity == null)
            throw new ProductServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        productRepository.delete(productEntity);
    }

    @Override
    public List<ProductDto> getProducts(int page, int limit) {
        List<ProductDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<ProductEntity> productPage = productRepository.findAll(pageableRequest);
        List<ProductEntity> products = productPage.getContent();

        for (ProductEntity productEntity : products) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(productEntity, productDto);
            returnValue.add(productDto);
        }

        return returnValue;
    }

    @Override
    public List<ProductDto> getProductByProductName(String productName, int page, int limit) {
        List<ProductDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ProductEntity> productPage = productRepository.findByProductNameContainingOrderByIdAsc(productName, pageableRequest);
        List<ProductEntity> products = productPage.getContent();

        for (ProductEntity productEntity : products) {
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(productEntity, productDto);
            returnValue.add(productDto);
        }

        return returnValue;
    }
}