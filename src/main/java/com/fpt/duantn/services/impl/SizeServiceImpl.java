package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.SizeServiceException;
import com.fpt.duantn.io.entity.SizeEntity;
import com.fpt.duantn.io.repository.SizeRepository;
import com.fpt.duantn.services.SizeService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.SizeDto;
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
public class SizeServiceImpl implements SizeService {

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    Utils utils;

    @Override
    public SizeDto createSize(SizeDto size) {
        // Kiểm tra xem sizeCode đã tồn tại hay chưa
        if (sizeRepository.findBySizeCode(size.getSizeCode()) != null) {
            throw new SizeServiceException("Size with the same code already exists");
        }

        // Chuyển đổi SizeDto thành SizeEntity
        ModelMapper modelMapper = new ModelMapper();
        SizeEntity sizeEntity = modelMapper.map(size, SizeEntity.class);

        // Tạo một mã ngẫu nhiên cho sizeCode (tùy theo yêu cầu)
        String publicSizeCode = utils.generateColorCode(8);
        sizeEntity.setSizeCode(publicSizeCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        SizeEntity storedSizeDetails = sizeRepository.save(sizeEntity);

        // Chuyển đổi SizeEntity thành SizeDto
        SizeDto returnValue = modelMapper.map(storedSizeDetails, SizeDto.class);

        return returnValue;
    }



    @Override
    public SizeDto getSizeById(Long sizeId) {
        SizeDto returnValue = new SizeDto();
        SizeEntity sizeEntity = sizeRepository.findSizeEntityById(sizeId);

        if (sizeEntity == null)
            throw new SizeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(sizeEntity, returnValue);

        return returnValue;
    }

    @Override
    public SizeDto updateSize(Long sizeId, SizeDto size) {
        SizeDto returnValue = new SizeDto();

        SizeEntity sizeEntity = sizeRepository.findSizeEntityById(sizeId);

        if (sizeEntity == null)
            throw new SizeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        sizeEntity.setSizeName(size.getSizeName());

        SizeEntity updatedSizes = sizeRepository.save(sizeEntity);
        returnValue = new ModelMapper().map(updatedSizes, SizeDto.class);

        return returnValue;
    }

    @Override
    public void deleteSize(Long sizeId) {
        SizeEntity sizeEntity = sizeRepository.findSizeEntityById(sizeId);

        if (sizeEntity == null)
            throw new SizeServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        sizeRepository.delete(sizeEntity);
    }

    @Override
    public List<SizeDto> getSizes(int page, int limit, String filter) {
        List<SizeDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<SizeEntity> sizePage = sizeRepository.filter(filter, pageableRequest);
        List<SizeEntity> sizes = sizePage.getContent();

        for (SizeEntity sizeEntity : sizes) {
            SizeDto sizeDto = new SizeDto();
            BeanUtils.copyProperties(sizeEntity, sizeDto);
            returnValue.add(sizeDto);
        }

        return returnValue;
    }

    @Override
    public Long count(String filter) {
        Long total = sizeRepository.count(filter);
        return total;
    }


}
