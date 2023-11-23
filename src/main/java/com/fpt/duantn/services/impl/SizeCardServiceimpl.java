package com.fpt.duantn.services.impl;

import com.fpt.duantn.io.entity.SizeEntity;
import com.fpt.duantn.io.repository.SizeRepository;
import com.fpt.duantn.services.SizeCardService;
import com.fpt.duantn.shrared.dto.CRUD.SizeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeCardServiceimpl implements SizeCardService {

    @Autowired
    SizeRepository sizeRepository;

    @Override
    public List<SizeDto> getSizeCard(int page, int limit) {
        List<SizeDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<SizeEntity> sizeCardPage = sizeRepository.getSizes(pageableRequest);
        List<SizeEntity> sizeCart = sizeCardPage.getContent();

        for (SizeEntity categoryCartEntity : sizeCart) {
            SizeDto categoryDto = new SizeDto();
            BeanUtils.copyProperties(categoryCartEntity, categoryDto);
            returnValue.add(categoryDto);
        }

        return returnValue;

    }
}
