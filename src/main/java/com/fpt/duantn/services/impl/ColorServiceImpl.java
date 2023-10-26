package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.ColorServiceException;
import com.fpt.duantn.io.entity.ColorEntity;
import com.fpt.duantn.io.repository.ColorRepository;
import com.fpt.duantn.services.ColorService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.ColorDto;
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
public class ColorServiceImpl implements ColorService {

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    Utils utils;

    @Override
    public ColorDto createColor(ColorDto color) {
        // Kiểm tra xem colorCode đã tồn tại hay chưa
        if (colorRepository.findByColorCode(color.getColorCode()) != null) {
            throw new ColorServiceException("Color with the same code already exists");
        }

        // Chuyển đổi ColorDto thành ColorEntity
        ModelMapper modelMapper = new ModelMapper();
        ColorEntity colorEntity = modelMapper.map(color, ColorEntity.class);

        // Tạo một mã ngẫu nhiên cho colorCode (tùy theo yêu cầu)
        String publicColorCode = utils.generateColorCode(8);
        colorEntity.setColorCode(publicColorCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        ColorEntity storedColorDetails = colorRepository.save(colorEntity);

        // Chuyển đổi ColorEntity thành ColorDto
        ColorDto returnValue = modelMapper.map(storedColorDetails, ColorDto.class);

        return returnValue;
    }



    @Override
    public ColorDto getColorByColorCode(String colorCode) {
        ColorDto returnValue = new ColorDto();
        ColorEntity colorEntity = colorRepository.findByColorCode(colorCode);

        if (colorEntity == null)
            throw new ColorServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(colorEntity, returnValue);

        return returnValue;
    }

    @Override
    public ColorDto updateColor(String colorCode, ColorDto color) {
        ColorDto returnValue = new ColorDto();

        ColorEntity colorEntity = colorRepository.findByColorCode(colorCode);

        if (colorEntity == null)
            throw new ColorServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        colorEntity.setColorName(color.getColorName());

        ColorEntity updatedColors = colorRepository.save(colorEntity);
        returnValue = new ModelMapper().map(updatedColors, ColorDto.class);

        return returnValue;
    }

    @Override
    public void deleteColor(String colorCode) {
        ColorEntity colorEntity = colorRepository.findByColorCode(colorCode);

        if (colorEntity == null)
            throw new ColorServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        colorRepository.delete(colorEntity);
    }

    @Override
    public List<ColorDto> getColors(int page, int limit) {
        List<ColorDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<ColorEntity> colorPage = colorRepository.findAll(pageableRequest);
        List<ColorEntity> colors = colorPage.getContent();

        for (ColorEntity colorEntity : colors) {
            ColorDto colorDto = new ColorDto();
            BeanUtils.copyProperties(colorEntity, colorDto);
            returnValue.add(colorDto);
        }

        return returnValue;
    }

    @Override
    public List<ColorDto> getColorByColorName(String colorName, int page, int limit) {
        List<ColorDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ColorEntity> colorPage = colorRepository.findByColorNameContainingOrderByIdAsc(colorName, pageableRequest);
        List<ColorEntity> colors = colorPage.getContent();

        for (ColorEntity colorEntity : colors) {
            ColorDto colorDto = new ColorDto();
            BeanUtils.copyProperties(colorEntity, colorDto);
            returnValue.add(colorDto);
        }

        return returnValue;
    }


}
