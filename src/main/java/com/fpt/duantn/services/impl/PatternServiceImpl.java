package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.PatternServiceException;
import com.fpt.duantn.io.entity.PatternEntity;
import com.fpt.duantn.io.repository.PatternRepository;
import com.fpt.duantn.services.PatternService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.PatternDto;
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
public class PatternServiceImpl implements PatternService {
    
    @Autowired
    PatternRepository patternRepository;
    
    @Autowired
    Utils utils;

    @Override
    public PatternDto createPattern(PatternDto pattern) {
        // Kiểm tra xem patternCode đã tồn tại hay chưa
        if (patternRepository.findByPatternCode(pattern.getPatternCode()) != null) {
            throw new PatternServiceException("Pattern with the same code already exists");
        }

        // Chuyển đổi PatternDto thành PatternEntity
        ModelMapper modelMapper = new ModelMapper();
        PatternEntity patternEntity = modelMapper.map(pattern, PatternEntity.class);

        // Tạo một mã ngẫu nhiên cho patternCode (tùy theo yêu cầu)
        String publicPatternCode = utils.generateColorCode(8);
        patternEntity.setPatternCode(publicPatternCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        PatternEntity storedPatternDetails = patternRepository.save(patternEntity);

        // Chuyển đổi PatternEntity thành PatternDto
        PatternDto returnValue = modelMapper.map(storedPatternDetails, PatternDto.class);

        return returnValue;
    }



    @Override
    public PatternDto getPatternByPatternCode(String patternCode) {
        PatternDto returnValue = new PatternDto();
        PatternEntity patternEntity = patternRepository.findByPatternCode(patternCode);

        if (patternEntity == null)
            throw new PatternServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(patternEntity, returnValue);

        return returnValue;
    }

    @Override
    public PatternDto updatePattern(String patternCode, PatternDto pattern) {
        PatternDto returnValue = new PatternDto();

        PatternEntity patternEntity = patternRepository.findByPatternCode(patternCode);

        if (patternEntity == null)
            throw new PatternServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        patternEntity.setPatternName(pattern.getPatternName());

        PatternEntity updatedPatterns = patternRepository.save(patternEntity);
        returnValue = new ModelMapper().map(updatedPatterns, PatternDto.class);

        return returnValue;
    }

    @Override
    public void deletePattern(String patternCode) {
        PatternEntity patternEntity = patternRepository.findByPatternCode(patternCode);

        if (patternEntity == null)
            throw new PatternServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        patternRepository.delete(patternEntity);
    }

    @Override
    public List<PatternDto> getPatterns(int page, int limit) {
        List<PatternDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<PatternEntity> patternPage = patternRepository.findAll(pageableRequest);
        List<PatternEntity> patterns = patternPage.getContent();

        for (PatternEntity patternEntity : patterns) {
            PatternDto patternDto = new PatternDto();
            BeanUtils.copyProperties(patternEntity, patternDto);
            returnValue.add(patternDto);
        }

        return returnValue;
    }

    @Override
    public List<PatternDto> getPatternByPatternName(String patternName, int page, int limit) {
        List<PatternDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<PatternEntity> patternPage = patternRepository.findByPatternNameContainingOrderByIdAsc(patternName, pageableRequest);
        List<PatternEntity> patterns = patternPage.getContent();

        for (PatternEntity patternEntity : patterns) {
            PatternDto patternDto = new PatternDto();
            BeanUtils.copyProperties(patternEntity, patternDto);
            returnValue.add(patternDto);
        }

        return returnValue;
    }
}
