package com.fpt.duantn.services.impl;

import com.fpt.duantn.io.entity.MaterialEntity;
import com.fpt.duantn.io.repository.MaterialRepository;
import com.fpt.duantn.services.MaterialCardService;
import com.fpt.duantn.shrared.dto.CRUD.MaterialDto;
import com.fpt.duantn.shrared.dto.CRUD.MaterialDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MaterialCardServiceimpl implements MaterialCardService {
    
    @Autowired
    MaterialRepository materialRepository;

    @Override
    public List<MaterialDto> getMaterialCard(int page, int limit) {
        List<MaterialDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<MaterialEntity> materialCardPage = materialRepository.getMaterials(pageableRequest);
        List<MaterialEntity> materialCart = materialCardPage.getContent();

        for (MaterialEntity categoryCartEntity : materialCart) {
            MaterialDto categoryDto = new MaterialDto();
            BeanUtils.copyProperties(categoryCartEntity, categoryDto);
            returnValue.add(categoryDto);
        }

        return returnValue;
    }
}
