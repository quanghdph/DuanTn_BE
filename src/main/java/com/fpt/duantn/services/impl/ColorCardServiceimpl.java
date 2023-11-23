package com.fpt.duantn.services.impl;

import com.fpt.duantn.io.entity.ColorEntity;
import com.fpt.duantn.io.repository.ColorRepository;
import com.fpt.duantn.services.ColorCardService;
import com.fpt.duantn.shrared.dto.CRUD.ColorDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorCardServiceimpl implements ColorCardService {

    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<ColorDto> getColorCard(int page, int limit) {
        List<ColorDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<ColorEntity> colorCardPage = colorRepository.getColors(pageableRequest);
        List<ColorEntity> colorCart = colorCardPage.getContent();

        for (ColorEntity colorCartEntity : colorCart) {
            ColorDto colorDto = new ColorDto();
            BeanUtils.copyProperties(colorCartEntity, colorDto);
            returnValue.add(colorDto);
        }

        return returnValue;
    }
}
