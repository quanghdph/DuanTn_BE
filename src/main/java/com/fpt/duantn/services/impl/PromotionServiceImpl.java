package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.PromotionServiceException;
import com.fpt.duantn.io.entity.PromotionEntity;
import com.fpt.duantn.io.repository.PromotionRepository;
import com.fpt.duantn.services.PromotionService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.PromotionDto;
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
public class PromotionServiceImpl implements PromotionService {

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    Utils utils;

    @Override
    public PromotionDto createPromotion(PromotionDto promotion) {
        // Kiểm tra xem PromotionCode đã tồn tại hay chưa
        if (promotionRepository.findByPromotionCode(promotion.getPromotionCode()) != null) {
            throw new PromotionServiceException("Promotion with the same code already exists");
        }

        // Chuyển đổi PromotionDto thành PromotionEntity
        ModelMapper modelMapper = new ModelMapper();
        PromotionEntity promotionEntity = modelMapper.map(promotion, PromotionEntity.class);

        // Tạo một mã ngẫu nhiên cho PromotionCode (tùy theo yêu cầu)
        String publicPromotionCode = utils.generateColorCode(8);
        promotionEntity.setPromotionCode(publicPromotionCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        PromotionEntity storedPromotionDetails = promotionRepository.save(promotionEntity);

        // Chuyển đổi PromotionEntity thành PromotionDto
        PromotionDto returnValue = modelMapper.map(storedPromotionDetails, PromotionDto.class);

        return returnValue;
    }



    @Override
    public PromotionDto getPromotionByPromotionCode(String promotionCode) {
        PromotionDto returnValue = new PromotionDto();
        PromotionEntity promotionEntity = promotionRepository.findByPromotionCode(promotionCode);

        if (promotionEntity == null)
            throw new PromotionServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(promotionEntity, returnValue);

        return returnValue;
    }

    @Override
    public PromotionDto updatePromotion(String promotionCode, PromotionDto promotion) {
        PromotionDto returnValue = new PromotionDto();

        PromotionEntity promotionEntity = promotionRepository.findByPromotionCode(promotionCode);

        if (promotionEntity == null)
            throw new PromotionServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        promotionEntity.setAmount(promotion.getAmount());
        promotionEntity.setStatus(promotion.getStatus());
        promotionEntity.setUpdateTime(promotion.getUpdateTime());
        promotionEntity.setCreateDate(promotion.getCreateDate());
        promotionEntity.setDescription(promotion.getDescription());
        promotionEntity.setDiscountLevel(promotion.getDiscountLevel());

        PromotionEntity updatedPromotions = promotionRepository.save(promotionEntity);
        returnValue = new ModelMapper().map(updatedPromotions, PromotionDto.class);

        return returnValue;
    }

    @Override
    public void deletePromotion(String promotionCode) {
        PromotionEntity promotionEntity = promotionRepository.findByPromotionCode(promotionCode);

        if (promotionEntity == null)
            throw new PromotionServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        promotionRepository.delete(promotionEntity);
    }

    @Override
    public List<PromotionDto> getPromotions(int page, int limit, String filter) {
        List<PromotionDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<PromotionEntity> promotionPage = promotionRepository.filter(filter, pageableRequest);
        List<PromotionEntity> promotions = promotionPage.getContent();

        for (PromotionEntity promotionEntity : promotions) {
            PromotionDto promotionDto = new PromotionDto();
            BeanUtils.copyProperties(promotionEntity, promotionDto);
            returnValue.add(promotionDto);
        }

        return returnValue;
    }

    @Override
    public Long count(String filter) {

        Long total = promotionRepository.count(filter);
        return total;
    }

    @Override
    public List<PromotionDto> getPromotionByPromotionName(String promotionName, int page, int limit) {
        List<PromotionDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<PromotionEntity> promotionPage = promotionRepository.findByAmountContainingOrderByIdAsc(promotionName, pageableRequest);
        List<PromotionEntity> promotions = promotionPage.getContent();

        for (PromotionEntity promotionEntity : promotions) {
            PromotionDto promotionDto = new PromotionDto();
            BeanUtils.copyProperties(promotionEntity, promotionDto);
            returnValue.add(promotionDto);
        }

        return returnValue;
    }
}
