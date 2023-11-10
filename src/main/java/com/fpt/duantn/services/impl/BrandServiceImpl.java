package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.BrandServiceException;
import com.fpt.duantn.io.entity.BrandEntity;
import com.fpt.duantn.io.repository.BrandRepository;
import com.fpt.duantn.services.BrandService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.BrandDto;
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
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    Utils utils;

    @Override
    public BrandDto createBrand(BrandDto brand) {
        // Kiểm tra xem BrandCode đã tồn tại hay chưa
        if (brandRepository.findByBrandCode(brand.getBrandCode()) != null) {
            throw new BrandServiceException("Brand with the same code already exists");
        }

        // Chuyển đổi BrandDto thành BrandEntity
        ModelMapper modelMapper = new ModelMapper();
        BrandEntity brandEntity = modelMapper.map(brand, BrandEntity.class);

        // Tạo một mã ngẫu nhiên cho BrandCode (tùy theo yêu cầu)
        String publicBrandCode = utils.generateColorCode(8);
        brandEntity.setBrandCode(publicBrandCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        BrandEntity storedBrandDetails = brandRepository.save(brandEntity);

        // Chuyển đổi BrandEntity thành BrandDto
        BrandDto returnValue = modelMapper.map(storedBrandDetails, BrandDto.class);

        return returnValue;
    }



    @Override
    public BrandDto getBrandByBrandCode(String brandCode) {
        BrandDto returnValue = new BrandDto();
        BrandEntity brandEntity = brandRepository.findByBrandCode(brandCode);

        if (brandEntity == null)
            throw new BrandServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(brandEntity, returnValue);

        return returnValue;
    }

    @Override
    public BrandDto updateBrand(String brandCode, BrandDto brand) {
        BrandDto returnValue = new BrandDto();

        BrandEntity brandEntity = brandRepository.findByBrandCode(brandCode);

        if (brandEntity == null)
            throw new BrandServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        brandEntity.setBrandName(brand.getBrandName());
        brandEntity.setStatus(brand.getStatus());
        brandEntity.setUpdateDate(brand.getUpdateDate());
        brandEntity.setCreateDate(brand.getCreateDate());

        BrandEntity updatedBrands = brandRepository.save(brandEntity);
        returnValue = new ModelMapper().map(updatedBrands, BrandDto.class);

        return returnValue;
    }

    @Override
    public void deleteBrand(String brandCode) {
        BrandEntity brandEntity = brandRepository.findByBrandCode(brandCode);

        if (brandEntity == null)
            throw new BrandServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        brandRepository.delete(brandEntity);
    }

    @Override
    public List<BrandDto> getBrands(int page, int limit, String filter) {
        List<BrandDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<BrandEntity> brandPage = brandRepository.filter(filter, pageableRequest);
        List<BrandEntity> brands = brandPage.getContent();

        for (BrandEntity brandEntity : brands) {
            BrandDto brandDto = new BrandDto();
            BeanUtils.copyProperties(brandEntity, brandDto);
            returnValue.add(brandDto);
        }

        return returnValue;
    }

    @Override
    public Long count(String filter) {

        Long total = brandRepository.count(filter);
        return total;
    }

    @Override
    public List<BrandDto> getBrandByBrandName(String brandName, int page, int limit) {
        List<BrandDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<BrandEntity> brandPage = brandRepository.findByBrandNameContainingOrderByIdAsc(brandName, pageableRequest);
        List<BrandEntity> brands = brandPage.getContent();

        for (BrandEntity brandEntity : brands) {
            BrandDto brandDto = new BrandDto();
            BeanUtils.copyProperties(brandEntity, brandDto);
            returnValue.add(brandDto);
        }

        return returnValue;
    }
}
