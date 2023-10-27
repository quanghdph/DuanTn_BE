package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.PromotionDetailServiceException;
import com.fpt.duantn.io.entity.PromotionDetailEntity;
import com.fpt.duantn.io.repository.PromotionDetailRepository;
import com.fpt.duantn.services.PromotionDetailService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.PromotionDetailDto;
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
public class PromotionDetailServiceImpl implements PromotionDetailService {

    @Autowired
    PromotionDetailRepository promotionDetailRepository;

    @Autowired
    Utils utils;

    @Override
    public PromotionDetailDto createPromotionDetail(PromotionDetailDto promotionDetail) {
        // Kiểm tra xem PromotionDetailCode đã tồn tại hay chưa
        if (promotionDetailRepository.findPromotionDetailEntityById(promotionDetail.getId()) != null) {
            throw new PromotionDetailServiceException("PromotionDetail with the same code already exists");
        }

        // Chuyển đổi PromotionDetailDto thành PromotionDetailEntity
        ModelMapper modelMapper = new ModelMapper();
        PromotionDetailEntity promotionDetailEntity = modelMapper.map(promotionDetail, PromotionDetailEntity.class);

        // Tạo một mã ngẫu nhiên cho PromotionDetailCode (tùy theo yêu cầu)
//        String publicPromotionDetailCode = utils.generateColorCode(8);
//        PromotionDetailEntity.setId(publicPromotionDetailCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        PromotionDetailEntity storedPromotionDetailDetails = promotionDetailRepository.save(promotionDetailEntity);

        // Chuyển đổi PromotionDetailEntity thành PromotionDetailDto
        PromotionDetailDto returnValue = modelMapper.map(storedPromotionDetailDetails, PromotionDetailDto.class);

        return returnValue;
    }



    @Override
    public PromotionDetailDto getPromotionDetailByPromotionDetailCode(Long promotionDetailCode) {
        PromotionDetailDto returnValue = new PromotionDetailDto();
        PromotionDetailEntity promotionDetailEntity = promotionDetailRepository.findPromotionDetailEntityById(promotionDetailCode);

        if (promotionDetailEntity == null)
            throw new PromotionDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(promotionDetailEntity, returnValue);

        return returnValue;
    }

    @Override
    public PromotionDetailDto updatePromotionDetail(Long promotionDetailCode, PromotionDetailDto promotionDetail) {
        PromotionDetailDto returnValue = new PromotionDetailDto();

        PromotionDetailEntity promotionDetailEntity = promotionDetailRepository.findPromotionDetailEntityById(promotionDetailCode);

        if (promotionDetailEntity == null)
            throw new PromotionDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());


        promotionDetailEntity.setStatus(promotionDetail.getStatus());
        promotionDetailEntity.setUpdateDate(promotionDetail.getUpdateDate());
        promotionDetailEntity.setCreateDate(promotionDetail.getCreateDate());


        PromotionDetailEntity updatedPromotionDetails = promotionDetailRepository.save(promotionDetailEntity);
        returnValue = new ModelMapper().map(updatedPromotionDetails, PromotionDetailDto.class);

        return returnValue;
    }

    @Override
    public void deletePromotionDetail(Long promotionDetailCode) {
        PromotionDetailEntity promotionDetailEntity = promotionDetailRepository.findPromotionDetailEntityById(promotionDetailCode);

        if (promotionDetailEntity == null)
            throw new PromotionDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        promotionDetailRepository.delete(promotionDetailEntity);
    }

    @Override
    public List<PromotionDetailDto> getPromotionDetails(int page, int limit) {
        List<PromotionDetailDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<PromotionDetailEntity> promotionDetailPage = promotionDetailRepository.findAll(pageableRequest);
        List<PromotionDetailEntity> promotionDetails = promotionDetailPage.getContent();

        for (PromotionDetailEntity promotionDetailEntity : promotionDetails) {
            PromotionDetailDto promotionDetailDto = new PromotionDetailDto();
            BeanUtils.copyProperties(promotionDetailEntity, promotionDetailDto);
            returnValue.add(promotionDetailDto);
        }

        return returnValue;
    }

//    @Override
//    public List<PromotionDetailDto> getPromotionDetailByPromotionDetailName(String promotionDetailName, int page, int limit) {
//        List<PromotionDetailDto> returnValue = new ArrayList<>();
//
//        if(page>0) page = page-1;
//
//        Pageable pageableRequest = PageRequest.of(page, limit);
//        Page<PromotionDetailEntity> promotionDetailPage = promotionDetailRepository.findByPromotionDetailContainingOrderByIdAsc(promotionDetailName, pageableRequest);
//        List<PromotionDetailEntity> promotionDetails = promotionDetailPage.getContent();
//
//        for (PromotionDetailEntity promotionDetailEntity : promotionDetails) {
//            PromotionDetailDto promotionDetailDto = new PromotionDetailDto();
//            BeanUtils.copyProperties(promotionDetailEntity, promotionDetailDto);
//            returnValue.add(promotionDetailDto);
//        }
//
//        return returnValue;
//    }
}
