package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.ProductDetailServiceException;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.repository.ProductDetailRepository;
import com.fpt.duantn.services.ProductDetailService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.ProductDetailDto;
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
import java.util.Optional;
import java.util.UUID;

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

        //them khoa ngoai
        productDetailEntity.setProduct(productDetail.getProduct());
        productDetailEntity.setColor(productDetail.getColor());
        productDetailEntity.setSize(productDetail.getSize());

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        ProductDetailEntity storedProductDetailDetails = productDetailRepository.save(productDetailEntity);

        // Chuyển đổi ProductDetailEntity thành ProductDetailDto
        ProductDetailDto returnValue = modelMapper.map(storedProductDetailDetails, ProductDetailDto.class);

        return returnValue;
    }

    @Override
    public ProductDetailDto getProductDetailById(Long productDetailId) {
        ProductDetailDto returnValue = new ProductDetailDto();
        ProductDetailEntity productDetailEntity = productDetailRepository.findProductDetailEntityById(productDetailId);

        if (productDetailEntity == null)
            throw new ProductDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(productDetailEntity, returnValue);

        return returnValue;
    }

    @Override
    public Optional<ProductDetailEntity> findById(Long aLong) {
        return productDetailRepository.findById(aLong);
    }

    @Override
    public ProductDetailDto updateProductDetail(Long productDetailId, ProductDetailDto productDetail) {
        ProductDetailDto returnValue = new ProductDetailDto();

        ProductDetailEntity productDetailEntity = productDetailRepository.findProductDetailEntityById(productDetailId);

        if (productDetailEntity == null)
            throw new ProductDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());


        productDetailEntity.setStatus(productDetail.getStatus());
        productDetailEntity.setUpdateDate(productDetail.getUpdateDate());
        productDetailEntity.setCreateDate(productDetail.getCreateDate());
        productDetailEntity.setProduct(productDetail.getProduct());
        productDetailEntity.setQuantity(productDetail.getQuantity());
        productDetailEntity.setColor(productDetail.getColor());
        productDetailEntity.setPrice(productDetail.getPrice());
        productDetailEntity.setSize(productDetail.getSize());

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
    public List<ProductDetailDto> getProductsDetail(int page, int limit, String filter) {
        List<ProductDetailDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<ProductDetailEntity> productDetailPage = productDetailRepository.filter(filter, pageableRequest);
        List<ProductDetailEntity> productsDetails = productDetailPage.getContent();

        for (ProductDetailEntity productDetailEntity : productsDetails) {
            ProductDetailDto productDetailDto = new ProductDetailDto();
            BeanUtils.copyProperties(productDetailEntity, productDetailDto);
            returnValue.add(productDetailDto);
        }

        return returnValue;
    }

    @Override
    public List<ProductDetailDto> getProductsDetail(Long idProduct,int page, int limit, String filter) {
        List<ProductDetailDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<ProductDetailEntity> productDetailPage = productDetailRepository.filter(idProduct,filter, pageableRequest);
        List<ProductDetailEntity> productsDetails = productDetailPage.getContent();

        for (ProductDetailEntity productDetailEntity : productsDetails) {
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

    @Override
    public ProductDetailDto getProductDetailsByProductAndColorAndSize(Long productId,Long colorId, Long sizeId) {
        ProductDetailEntity productDetailEntity = productDetailRepository.findByProductIdAndColorIdAndSizeId(productId ,colorId, sizeId);
        if (productDetailEntity != null) {
            return convertToDto(productDetailEntity);
        } else {
            return null;
        }
    }

    @Override
    public Optional<Double> sumMoneyByBillIdAndType(Long id, Integer type) {
        return Optional.empty();
    }

    @Override
    public <S extends ProductDetailEntity> List<S> saveAll(Iterable<S> entities) {
        return productDetailRepository.saveAll(entities);
    }

    @Override
    public <S extends ProductDetailEntity> S save(S entity) {
        return productDetailRepository.save(entity);
    }

    private ProductDetailDto convertToDto(ProductDetailEntity productDetailEntity) {

        ProductDetailDto dto = new ProductDetailDto();
        dto.setId(productDetailEntity.getId());
        dto.setProduct(productDetailEntity.getProduct());
        dto.setColor(productDetailEntity.getColor());
        dto.setSize(productDetailEntity.getSize());
        dto.setPrice(productDetailEntity.getPrice());
        dto.setQuantity(productDetailEntity.getQuantity());
        dto.setCreateDate(productDetailEntity.getCreateDate());
        dto.setUpdateDate(productDetailEntity.getUpdateDate());
        dto.setStatus(productDetailEntity.getStatus());

        return dto;
    }

}
