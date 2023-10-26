package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.DesignServiceException;
import com.fpt.duantn.io.entity.DesignEntity;
import com.fpt.duantn.io.repository.DesignRepository;
import com.fpt.duantn.services.DesignService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.DesignDto;
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
public class DesignServiceImpl implements DesignService {

    @Autowired
    DesignRepository designRepository;

    @Autowired
    Utils utils;

    @Override
    public DesignDto createDesign(DesignDto design) {
        // Kiểm tra xem designCode đã tồn tại hay chưa
        if (designRepository.findByDesignCode(design.getDesignCode()) != null) {
            throw new DesignServiceException("Design with the same code already exists");
        }

        // Chuyển đổi DesignDto thành DesignEntity
        ModelMapper modelMapper = new ModelMapper();
        DesignEntity designEntity = modelMapper.map(design, DesignEntity.class);

        // Tạo một mã ngẫu nhiên cho designCode (tùy theo yêu cầu)
        String publicDesignCode = utils.generateColorCode(8);
        designEntity.setDesignCode(publicDesignCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        DesignEntity storedDesignDetails = designRepository.save(designEntity);

        // Chuyển đổi DesignEntity thành DesignDto
        DesignDto returnValue = modelMapper.map(storedDesignDetails, DesignDto.class);

        return returnValue;
    }



    @Override
    public DesignDto getDesignByDesignCode(String designCode) {
        DesignDto returnValue = new DesignDto();
        DesignEntity designEntity = designRepository.findByDesignCode(designCode);

        if (designEntity == null)
            throw new DesignServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(designEntity, returnValue);

        return returnValue;
    }

    @Override
    public DesignDto updateDesign(String designCode, DesignDto design) {
        DesignDto returnValue = new DesignDto();

        DesignEntity designEntity = designRepository.findByDesignCode(designCode);

        if (designEntity == null)
            throw new DesignServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        designEntity.setDesignName(design.getDesignName());

        DesignEntity updatedDesigns = designRepository.save(designEntity);
        returnValue = new ModelMapper().map(updatedDesigns, DesignDto.class);

        return returnValue;
    }

    @Override
    public void deleteDesign(String designCode) {
        DesignEntity designEntity = designRepository.findByDesignCode(designCode);

        if (designEntity == null)
            throw new DesignServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        designRepository.delete(designEntity);
    }

    @Override
    public List<DesignDto> getDesigns(int page, int limit) {
        List<DesignDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<DesignEntity> designPage = designRepository.findAll(pageableRequest);
        List<DesignEntity> designs = designPage.getContent();

        for (DesignEntity designEntity : designs) {
            DesignDto designDto = new DesignDto();
            BeanUtils.copyProperties(designEntity, designDto);
            returnValue.add(designDto);
        }

        return returnValue;
    }

    @Override
    public List<DesignDto> getDesignByDesignName(String designName, int page, int limit) {
        List<DesignDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<DesignEntity> designPage = designRepository.findByDesignNameContainingOrderByIdAsc(designName, pageableRequest);
        List<DesignEntity> designs = designPage.getContent();

        for (DesignEntity designEntity : designs) {
            DesignDto designDto = new DesignDto();
            BeanUtils.copyProperties(designEntity, designDto);
            returnValue.add(designDto);
        }

        return returnValue;
    }
}
