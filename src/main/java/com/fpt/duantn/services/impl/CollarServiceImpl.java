package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.CollarServiceException;
import com.fpt.duantn.io.entity.CollarEntity;
import com.fpt.duantn.io.repository.CollarRepository;
import com.fpt.duantn.services.CollarService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.CollarDto;
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
public class CollarServiceImpl implements CollarService {
    
    @Autowired
    CollarRepository collarRepository;
    
    @Autowired
    Utils utils;

    @Override
    public CollarDto createCollar(CollarDto collar) {
        // Kiểm tra xem CollarCode đã tồn tại hay chưa
        if (collarRepository.findByCollarCode(collar.getCollarCode()) != null) {
            throw new CollarServiceException("Collar with the same code already exists");
        }

        // Chuyển đổi CollarDto thành CollarEntity
        ModelMapper modelMapper = new ModelMapper();
        CollarEntity collarEntity = modelMapper.map(collar, CollarEntity.class);

        // Tạo một mã ngẫu nhiên cho CollarCode (tùy theo yêu cầu)
        String publicCollarCode = utils.generateColorCode(8);
        collarEntity.setCollarCode(publicCollarCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        CollarEntity storedCollarDetails = collarRepository.save(collarEntity);

        // Chuyển đổi CollarEntity thành CollarDto
        CollarDto returnValue = modelMapper.map(storedCollarDetails, CollarDto.class);

        return returnValue;
    }



    @Override
    public CollarDto getCollarByCollarCode(String collarCode) {
        CollarDto returnValue = new CollarDto();
        CollarEntity collarEntity = collarRepository.findByCollarCode(collarCode);

        if (collarEntity == null)
            throw new CollarServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(collarEntity, returnValue);

        return returnValue;
    }

    @Override
    public CollarDto updateCollar(String collarCode, CollarDto collar) {
        CollarDto returnValue = new CollarDto();

        CollarEntity collarEntity = collarRepository.findByCollarCode(collarCode);

        if (collarEntity == null)
            throw new CollarServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        collarEntity.setCollarName(collar.getCollarName());

        CollarEntity updatedCollars = collarRepository.save(collarEntity);
        returnValue = new ModelMapper().map(updatedCollars, CollarDto.class);

        return returnValue;
    }

    @Override
    public void deleteCollar(String collarCode) {
        CollarEntity collarEntity = collarRepository.findByCollarCode(collarCode);

        if (collarEntity == null)
            throw new CollarServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        collarRepository.delete(collarEntity);
    }

    @Override
    public List<CollarDto> getCollars(int page, int limit) {
        List<CollarDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<CollarEntity> collarPage = collarRepository.findAll(pageableRequest);
        List<CollarEntity> collars = collarPage.getContent();

        for (CollarEntity collarEntity : collars) {
            CollarDto collarDto = new CollarDto();
            BeanUtils.copyProperties(collarEntity, collarDto);
            returnValue.add(collarDto);
        }

        return returnValue;
    }

    @Override
    public List<CollarDto> getCollarByCollarName(String collarName, int page, int limit) {
        List<CollarDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<CollarEntity> collarPage = collarRepository.findByCollarNameContainingOrderByIdAsc(collarName, pageableRequest);
        List<CollarEntity> collars = collarPage.getContent();

        for (CollarEntity collarEntity : collars) {
            CollarDto collarDto = new CollarDto();
            BeanUtils.copyProperties(collarEntity, collarDto);
            returnValue.add(collarDto);
        }

        return returnValue;
    }
}
