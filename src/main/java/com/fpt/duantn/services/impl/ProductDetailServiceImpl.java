package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.ProductDetailServiceException;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.repository.ProductDetailRepository;
import com.fpt.duantn.services.ProductDetailService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;
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
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    Utils utils;

    @Override
    public ProductDetailDto createProductDetail(ProductDetailDto productDetail) {
        // Kiểm tra xem ProductDetailCode đã tồn tại hay chưa
        if (productDetailRepository.findProductDetailEntityById(productDetail.getId()) != null) {
            throw new ProductDetailServiceException("ProductDetail with the same code already exists");
        }

        // Chuyển đổi ProductDetailDto thành ProductDetailEntity
        ModelMapper modelMapper = new ModelMapper();
        ProductDetailEntity productDetailEntity = modelMapper.map(productDetail, ProductDetailEntity.class);

        // Tạo một mã ngẫu nhiên cho ProductDetailCode (tùy theo yêu cầu)
//        String publicProductDetailCode = utils.generateColorCode(8);
//        productDetailEntity.setId(publicProductDetailCode);

        //them khoa ngoai
        productDetailEntity.setProduct(productDetail.getProduct());
        productDetailEntity.setColor(productDetail.getColor());
        productDetailEntity.setSize(productDetail.getSize());
        productDetailEntity.setMaterial(productDetail.getMaterial());
        productDetailEntity.setPattern(productDetail.getPattern());
        productDetailEntity.setCollar(productDetail.getCollar());
        productDetailEntity.setWaistband(productDetail.getWaistband());

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        ProductDetailEntity storedProductDetailDetails = productDetailRepository.save(productDetailEntity);

        // Chuyển đổi ProductDetailEntity thành ProductDetailDto
        ProductDetailDto returnValue = modelMapper.map(storedProductDetailDetails, ProductDetailDto.class);

        return returnValue;
    }



    @Override
    public ProductDetailDto getProductDetailByProductDetailCode(Long productDetailCode) {
        ProductDetailDto returnValue = new ProductDetailDto();
        ProductDetailEntity productDetailEntity = productDetailRepository.findProductDetailEntityById(productDetailCode);

        if (productDetailEntity == null)
            throw new ProductDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(productDetailEntity, returnValue);

        return returnValue;
    }

    @Override
    public ProductDetailDto updateProductDetail(Long productDetailCode, ProductDetailDto productDetail) {
        ProductDetailDto returnValue = new ProductDetailDto();

        ProductDetailEntity productDetailEntity = productDetailRepository.findProductDetailEntityById(productDetailCode);

        if (productDetailEntity == null)
            throw new ProductDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());


        productDetailEntity.setStatus(productDetail.getStatus());
        productDetailEntity.setUpdateDate(productDetail.getUpdateDate());
        productDetailEntity.setCreateDate(productDetail.getCreateDate());
        productDetailEntity.setProductDetailCode(productDetail.getProductDetailCode());
        productDetailEntity.setProduct(productDetail.getProduct());
        productDetailEntity.setAmount(productDetail.getAmount());
        productDetailEntity.setCollar(productDetail.getCollar());
        productDetailEntity.setColor(productDetail.getColor());
        productDetailEntity.setDefaultPrice(productDetail.getDefaultPrice());
        productDetailEntity.setMaterial(productDetail.getMaterial());
        productDetailEntity.setPattern(productDetail.getPattern());
        productDetailEntity.setPrice(productDetail.getPrice());
        productDetailEntity.setSize(productDetail.getSize());
        productDetailEntity.setWaistband(productDetail.getWaistband());

        ProductDetailEntity updatedProductDetails = productDetailRepository.save(productDetailEntity);
        returnValue = new ModelMapper().map(updatedProductDetails, ProductDetailDto.class);

        return returnValue;
    }

    @Override
    public void deleteProductDetail(Long productDetailId) {
        ProductDetailEntity productDetailEntity = productDetailRepository.findProductDetailEntityById(productDetailId);

        if (productDetailEntity == null)
            throw new ProductDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        productDetailRepository.delete(productDetailEntity);
    }

    @Override
    public List<ProductDetailDto> getProductDetails(int page, int limit, String filter) {
        List<ProductDetailDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<ProductDetailEntity> productDetailPage = productDetailRepository.filter(filter, pageableRequest);
        List<ProductDetailEntity> productDetails = productDetailPage.getContent();

        for (ProductDetailEntity productDetailEntity : productDetails) {
            ProductDetailDto productDetailDto = new ProductDetailDto();
            BeanUtils.copyProperties(productDetailEntity, productDetailDto);
            returnValue.add(productDetailDto);
        }

        return returnValue;
    }

    @Override
    public Long count(String filter) {

        Long total = productDetailRepository.count(filter);
        return total;
    }

//    @Override
//    public List<ProductDetailDto> getProductDetailByProductDetailName(String productDetailName, int page, int limit) {
//        List<ProductDetailDto> returnValue = new ArrayList<>();
//
//        if(page>0) page = page-1;
//
//        Pageable pageableRequest = PageRequest.of(page, limit);
//        Page<ProductDetailEntity> productDetailPage = productDetailRepository.findByProductDetailContainingOrderByIdAsc(productDetailName, pageableRequest);
//        List<ProductDetailEntity> productDetails = productDetailPage.getContent();
//
//        for (ProductDetailEntity productDetailEntity : productDetails) {
//            ProductDetailDto productDetailDto = new ProductDetailDto();
//            BeanUtils.copyProperties(productDetailEntity, productDetailDto);
//            returnValue.add(productDetailDto);
//        }
//
//        return returnValue;
//    }
}
