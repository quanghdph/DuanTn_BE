package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.MaterialService;
import com.fpt.duantn.shrared.dto.CRUD.MaterialDto;
import com.fpt.duantn.ui.model.request.MaterialDetailsRequestModel;
import com.fpt.duantn.ui.model.response.MaterialRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialController {
    
    @Autowired
    MaterialService materialService;

    @GetMapping(path = "/{id}")
    public MaterialRest getMaterial(@PathVariable String id) {
        MaterialRest returnValue = new MaterialRest();

        MaterialDto materialDto = materialService.getMaterialByMaterialCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(materialDto, MaterialRest.class);

        return returnValue;
    }


    @PostMapping()
    public MaterialRest createMaterial(@RequestBody MaterialDetailsRequestModel materialDetails) throws Exception {
        MaterialRest returnValue = new MaterialRest();

        ModelMapper modelMapper = new ModelMapper();
        MaterialDto materialDto = modelMapper.map(materialDetails, MaterialDto.class);

        MaterialDto createdUser = materialService.createMaterial(materialDto);
        returnValue = modelMapper.map(createdUser, MaterialRest.class);

        return returnValue;
    }


    @GetMapping()
    public List<MaterialRest> getMaterials(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<MaterialRest> returnValue = new ArrayList<>();

        List<MaterialDto> materials = materialService.getMaterials(page, limit);

        for (MaterialDto materialDto : materials) {
            MaterialRest materialModel = new MaterialRest();
            BeanUtils.copyProperties(materialDto, materialModel);
            returnValue.add(materialModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public MaterialRest updateMaterial(@PathVariable String id, @RequestBody MaterialDetailsRequestModel materialDetails) {
        MaterialRest returnValue = new MaterialRest();

        MaterialDto materialDto = new MaterialDto();
        materialDto = new ModelMapper().map(materialDetails, MaterialDto.class);

        MaterialDto updateMaterial = materialService.updateMaterial(id, materialDto);
        returnValue = new ModelMapper().map(updateMaterial, MaterialRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteMaterial(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        materialService.deleteMaterial(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<MaterialRest> searchMaterials(@RequestParam(value = "materialName") String materialName,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<MaterialRest> returnValue = new ArrayList<>();

        List<MaterialDto> materials = materialService.getMaterialByMaterialName(materialName, page, limit);

        for (MaterialDto materialDto : materials) {
            MaterialRest materialModel = new MaterialRest();
            BeanUtils.copyProperties(materialDto, materialModel);
            returnValue.add(materialModel);
        }

        return returnValue;
    }
}
