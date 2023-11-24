package com.fpt.duantn.services.impl;

import com.fpt.duantn.io.entity.WaistbandEntity;
import com.fpt.duantn.io.repository.WaistbandRepository;
import com.fpt.duantn.services.WaistbandCardService;
import com.fpt.duantn.shrared.dto.CRUD.WaistbandDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WaistbandCardServiceimpl implements WaistbandCardService {

    @Autowired
    WaistbandRepository waistbandRepository;

    @Override
    public List<WaistbandDto> getWaistbandCard(int page, int limit) {
        List<WaistbandDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<WaistbandEntity> waistbandCardPage = waistbandRepository.getWaistbands(pageableRequest);
        List<WaistbandEntity> waistbandCart = waistbandCardPage.getContent();

        for (WaistbandEntity waistbandCartEntity : waistbandCart) {
            WaistbandDto waistbandDto = new WaistbandDto();
            BeanUtils.copyProperties(waistbandCartEntity, waistbandDto);
            returnValue.add(waistbandDto);
        }

        return returnValue;
    }
}
