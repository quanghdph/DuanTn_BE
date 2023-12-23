package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.MaterialService;
import com.fpt.duantn.shrared.dto.CRUD.MaterialDto;
import com.fpt.duantn.ui.model.request.MaterialRequest;
import com.fpt.duantn.ui.model.response.MaterialRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/material")
public class MaterialController {
    
    @Autowired
    MaterialService materialService;

    @GetMapping(path = "/{id}")
    public MaterialRest getMaterial(@PathVariable Long id) {
        MaterialRest returnValue = new MaterialRest();

        MaterialDto materialDto = materialService.getMaterialById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(materialDto, MaterialRest.class);

        return returnValue;
    }


    @PostMapping()
    public MaterialRest createMaterial(@RequestBody MaterialRequest materialDetails) throws Exception {
        MaterialRest returnValue = new MaterialRest();

        ModelMapper modelMapper = new ModelMapper();
        MaterialDto materialDto = modelMapper.map(materialDetails, MaterialDto.class);

        MaterialDto createdUser = materialService.createMaterial(materialDto);
        returnValue = modelMapper.map(createdUser, MaterialRest.class);

        return returnValue;
    }


    @GetMapping()
    public PaginationRest getMaterials(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "5") int limit,
                                           @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<MaterialRest> returnValue = new ArrayList<>();

        List<MaterialDto> materials = materialService.getMaterials(page, limit, filter);

        for (MaterialDto materialDto : materials) {
            MaterialRest materialModel = new MaterialRest();
            BeanUtils.copyProperties(materialDto, materialModel);
            returnValue.add(materialModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListMaterials(returnValue);
        paginationRest.setTotal(materialService.count(filter));

        return paginationRest;
    }


    @PutMapping(path = "/{id}")
    public MaterialRest updateMaterial(@PathVariable Long id, @RequestBody MaterialRequest materialDetails) {
        MaterialRest returnValue = new MaterialRest();

        MaterialDto materialDto = new MaterialDto();
        materialDto = new ModelMapper().map(materialDetails, MaterialDto.class);

        MaterialDto updateMaterial = materialService.updateMaterial(id, materialDto);
        returnValue = new ModelMapper().map(updateMaterial, MaterialRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteMaterial(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            materialService.deleteMaterial(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Chat Lieu: Chat Lieu có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Chat Lieu: " + e.getMessage());
        }return returnValue;
    }
}
