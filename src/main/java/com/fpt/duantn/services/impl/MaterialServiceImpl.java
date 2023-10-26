package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.MaterialServiceException;
import com.fpt.duantn.io.entity.MaterialEntity;
import com.fpt.duantn.io.repository.MaterialRepository;
import com.fpt.duantn.services.MaterialService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.MaterialDto;
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
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepository materialRepository;

    @Autowired
    Utils utils;

    @Override
    public MaterialDto createMaterial(MaterialDto material) {
        // Kiểm tra xem materialCode đã tồn tại hay chưa
        if (materialRepository.findByMaterialCode(material.getMaterialCode()) != null) {
            throw new MaterialServiceException("Material with the same code already exists");
        }

        // Chuyển đổi MaterialDto thành MaterialEntity
        ModelMapper modelMapper = new ModelMapper();
        MaterialEntity materialEntity = modelMapper.map(material, MaterialEntity.class);

        // Tạo một mã ngẫu nhiên cho materialCode (tùy theo yêu cầu)
        String publicMaterialCode = utils.generateColorCode(8);
        materialEntity.setMaterialCode(publicMaterialCode);

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        MaterialEntity storedMaterialDetails = materialRepository.save(materialEntity);

        // Chuyển đổi MaterialEntity thành MaterialDto
        MaterialDto returnValue = modelMapper.map(storedMaterialDetails, MaterialDto.class);

        return returnValue;
    }



    @Override
    public MaterialDto getMaterialByMaterialCode(String materialCode) {
        MaterialDto returnValue = new MaterialDto();
        MaterialEntity materialEntity = materialRepository.findByMaterialCode(materialCode);

        if (materialEntity == null)
            throw new MaterialServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(materialEntity, returnValue);

        return returnValue;
    }

    @Override
    public MaterialDto updateMaterial(String materialCode, MaterialDto material) {
        MaterialDto returnValue = new MaterialDto();

        MaterialEntity materialEntity = materialRepository.findByMaterialCode(materialCode);

        if (materialEntity == null)
            throw new MaterialServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        materialEntity.setMaterialName(material.getMaterialName());

        MaterialEntity updatedMaterials = materialRepository.save(materialEntity);
        returnValue = new ModelMapper().map(updatedMaterials, MaterialDto.class);

        return returnValue;
    }

    @Override
    public void deleteMaterial(String materialCode) {
        MaterialEntity materialEntity = materialRepository.findByMaterialCode(materialCode);

        if (materialEntity == null)
            throw new MaterialServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        materialRepository.delete(materialEntity);
    }

    @Override
    public List<MaterialDto> getMaterials(int page, int limit) {
        List<MaterialDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<MaterialEntity> materialPage = materialRepository.findAll(pageableRequest);
        List<MaterialEntity> materials = materialPage.getContent();

        for (MaterialEntity materialEntity : materials) {
            MaterialDto materialDto = new MaterialDto();
            BeanUtils.copyProperties(materialEntity, materialDto);
            returnValue.add(materialDto);
        }

        return returnValue;
    }

    @Override
    public List<MaterialDto> getMaterialByMaterialName(String materialName, int page, int limit) {
        List<MaterialDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<MaterialEntity> materialPage = materialRepository.findByMaterialNameContainingOrderByIdAsc(materialName, pageableRequest);
        List<MaterialEntity> materials = materialPage.getContent();

        for (MaterialEntity materialEntity : materials) {
            MaterialDto materialDto = new MaterialDto();
            BeanUtils.copyProperties(materialEntity, materialDto);
            returnValue.add(materialDto);
        }

        return returnValue;
    }
}
