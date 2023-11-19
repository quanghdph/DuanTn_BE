package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.WaistbandServiceException;
import com.fpt.duantn.io.entity.WaistbandEntity;
import com.fpt.duantn.io.repository.WaistbandRepository;
import com.fpt.duantn.services.WaistbandService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.WaistbandDto;
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
public class WaistbandServiceImpl implements WaistbandService {

    @Autowired
    WaistbandRepository waistbandRepository;

    @Autowired
    Utils utils;

    @Override
    public WaistbandDto createWaistband(WaistbandDto waistband) {
        // Kiểm tra xem WaistbandCode đã tồn tại hay chưa
        if (waistbandRepository.findByWaistbandCode(waistband.getWaistbandCode()) != null) {
            throw new WaistbandServiceException("Waistband with the same code already exists");
        }

        // Chuyển đổi WaistbandDto thành WaistbandEntity
        ModelMapper modelMapper = new ModelMapper();
        WaistbandEntity waistbandEntity = modelMapper.map(waistband, WaistbandEntity.class);

        // Tạo một mã ngẫu nhiên cho WaistbandCode (tùy theo yêu cầu)
        String publicWaistbandCode = utils.generateColorCode(8);
        waistbandEntity.setWaistbandCode(publicWaistbandCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        WaistbandEntity storedWaistbandDetails = waistbandRepository.save(waistbandEntity);

        // Chuyển đổi WaistbandEntity thành WaistbandDto
        WaistbandDto returnValue = modelMapper.map(storedWaistbandDetails, WaistbandDto.class);

        return returnValue;
    }



    @Override
    public WaistbandDto getWaistbandById(Long waistbandId) {
        WaistbandDto returnValue = new WaistbandDto();
        WaistbandEntity waistbandEntity = waistbandRepository.findWaistbandEntityById(waistbandId);

        if (waistbandEntity == null)
            throw new WaistbandServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(waistbandEntity, returnValue);

        return returnValue;
    }

    @Override
    public WaistbandDto updateWaistband(Long waistbandId, WaistbandDto waistband) {
        WaistbandDto returnValue = new WaistbandDto();

        WaistbandEntity waistbandEntity = waistbandRepository.findWaistbandEntityById(waistbandId);

        if (waistbandEntity == null)
            throw new WaistbandServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        waistbandEntity.setWaistbandName(waistband.getWaistbandName());

        WaistbandEntity updatedWaistbands = waistbandRepository.save(waistbandEntity);
        returnValue = new ModelMapper().map(updatedWaistbands, WaistbandDto.class);

        return returnValue;
    }

    @Override
    public void deleteWaistband(Long waistbandId) {
        WaistbandEntity waistbandEntity = waistbandRepository.findWaistbandEntityById(waistbandId);

        if (waistbandEntity == null)
            throw new WaistbandServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        waistbandRepository.delete(waistbandEntity);
    }

    @Override
    public List<WaistbandDto> getWaistbands(int page, int limit) {
        List<WaistbandDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<WaistbandEntity> waistbandPage = waistbandRepository.findAll(pageableRequest);
        List<WaistbandEntity> waistbands = waistbandPage.getContent();

        for (WaistbandEntity waistbandEntity : waistbands) {
            WaistbandDto waistbandDto = new WaistbandDto();
            BeanUtils.copyProperties(waistbandEntity, waistbandDto);
            returnValue.add(waistbandDto);
        }

        return returnValue;
    }

    @Override
    public List<WaistbandDto> getWaistbandByWaistbandName(String waistbandName, int page, int limit) {
        List<WaistbandDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<WaistbandEntity> waistbandPage = waistbandRepository.findByWaistbandNameContainingOrderByIdAsc(waistbandName, pageableRequest);
        List<WaistbandEntity> waistbands = waistbandPage.getContent();

        for (WaistbandEntity waistbandEntity : waistbands) {
            WaistbandDto waistbandDto = new WaistbandDto();
            BeanUtils.copyProperties(waistbandEntity, waistbandDto);
            returnValue.add(waistbandDto);
        }

        return returnValue;
    }
}
