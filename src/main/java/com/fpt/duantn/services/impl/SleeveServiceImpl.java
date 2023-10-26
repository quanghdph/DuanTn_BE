package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.SleeveServiceException;
import com.fpt.duantn.io.entity.SleeveEntity;
import com.fpt.duantn.io.repository.SleeveRepository;
import com.fpt.duantn.services.SleeveService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.SleeveDto;
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
public class SleeveServiceImpl implements SleeveService {

    @Autowired
    SleeveRepository sleeveRepository;

    @Autowired
    Utils utils;

    @Override
    public SleeveDto createSleeve(SleeveDto sleeve) {
        // Kiểm tra xem sleeveCode đã tồn tại hay chưa
        if (sleeveRepository.findBySleeveCode(sleeve.getSleeveCode()) != null) {
            throw new SleeveServiceException("Sleeve with the same code already exists");
        }

        // Chuyển đổi SleeveDto thành SleeveEntity
        ModelMapper modelMapper = new ModelMapper();
        SleeveEntity sleeveEntity = modelMapper.map(sleeve, SleeveEntity.class);

        // Tạo một mã ngẫu nhiên cho sleeveCode (tùy theo yêu cầu)
        String publicSleeveCode = utils.generateColorCode(8);
        sleeveEntity.setSleeveCode(publicSleeveCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        SleeveEntity storedSleeveDetails = sleeveRepository.save(sleeveEntity);

        // Chuyển đổi SleeveEntity thành SleeveDto
        SleeveDto returnValue = modelMapper.map(storedSleeveDetails, SleeveDto.class);

        return returnValue;
    }



    @Override
    public SleeveDto getSleeveBySleeveCode(String sleeveCode) {
        SleeveDto returnValue = new SleeveDto();
        SleeveEntity sleeveEntity = sleeveRepository.findBySleeveCode(sleeveCode);

        if (sleeveEntity == null)
            throw new SleeveServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(sleeveEntity, returnValue);

        return returnValue;
    }

    @Override
    public SleeveDto updateSleeve(String sleeveCode, SleeveDto sleeve) {
        SleeveDto returnValue = new SleeveDto();

        SleeveEntity sleeveEntity = sleeveRepository.findBySleeveCode(sleeveCode);

        if (sleeveEntity == null)
            throw new SleeveServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        sleeveEntity.setSleeveName(sleeve.getSleeveName());

        SleeveEntity updatedSleeves = sleeveRepository.save(sleeveEntity);
        returnValue = new ModelMapper().map(updatedSleeves, SleeveDto.class);

        return returnValue;
    }

    @Override
    public void deleteSleeve(String sleeveCode) {
        SleeveEntity sleeveEntity = sleeveRepository.findBySleeveCode(sleeveCode);

        if (sleeveEntity == null)
            throw new SleeveServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        sleeveRepository.delete(sleeveEntity);
    }

    @Override
    public List<SleeveDto> getSleeves(int page, int limit) {
        List<SleeveDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<SleeveEntity> sleevePage = sleeveRepository.findAll(pageableRequest);
        List<SleeveEntity> sleeves = sleevePage.getContent();

        for (SleeveEntity sleeveEntity : sleeves) {
            SleeveDto sleeveDto = new SleeveDto();
            BeanUtils.copyProperties(sleeveEntity, sleeveDto);
            returnValue.add(sleeveDto);
        }

        return returnValue;
    }

    @Override
    public List<SleeveDto> getSleeveBySleeveName(String sleeveName, int page, int limit) {
        List<SleeveDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<SleeveEntity> sleevePage = sleeveRepository.findBySleeveNameContainingOrderByIdAsc(sleeveName, pageableRequest);
        List<SleeveEntity> sleeves = sleevePage.getContent();

        for (SleeveEntity sleeveEntity : sleeves) {
            SleeveDto sleeveDto = new SleeveDto();
            BeanUtils.copyProperties(sleeveEntity, sleeveDto);
            returnValue.add(sleeveDto);
        }

        return returnValue;
    }


}
