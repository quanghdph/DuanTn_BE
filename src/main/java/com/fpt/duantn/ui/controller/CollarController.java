package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.CollarService;
import com.fpt.duantn.shrared.dto.CRUD.CollarDto;
import com.fpt.duantn.ui.model.request.CollarDetailsRequestModel;
import com.fpt.duantn.ui.model.response.CollarRest;
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
@RequestMapping("/collar")
public class CollarController {

    @Autowired
    CollarService collarService;

    @GetMapping(path = "/{id}")
    public CollarRest getCollar(@PathVariable String id) {
        CollarRest returnValue = new CollarRest();

        CollarDto collarDto = collarService.getCollarByCollarCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(collarDto, CollarRest.class);

        return returnValue;
    }

    @PostMapping()
    public CollarRest createCollar(@RequestBody CollarDetailsRequestModel collarDetails) throws Exception {
        CollarRest returnValue = new CollarRest();

        ModelMapper modelMapper = new ModelMapper();
        CollarDto collarDto = modelMapper.map(collarDetails, CollarDto.class);

        CollarDto createdUser = collarService.createCollar(collarDto);
        returnValue = modelMapper.map(createdUser, CollarRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<CollarRest> getCollars(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<CollarRest> returnValue = new ArrayList<>();

        List<CollarDto> collars = collarService.getCollars(page, limit);

        for (CollarDto collarDto : collars) {
            CollarRest collarModel = new CollarRest();
            BeanUtils.copyProperties(collarDto, collarModel);
            returnValue.add(collarModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public CollarRest updateCollar(@PathVariable String id, @RequestBody CollarDetailsRequestModel collarDetails) {
        CollarRest returnValue = new CollarRest();

        CollarDto collarDto = new CollarDto();
        collarDto = new ModelMapper().map(collarDetails, CollarDto.class);

        CollarDto updateCollar = collarService.updateCollar(id, collarDto);
        returnValue = new ModelMapper().map(updateCollar, CollarRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteCollar(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        collarService.deleteCollar(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<CollarRest> searchCollars(@RequestParam(value = "collarName") String collarName,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<CollarRest> returnValue = new ArrayList<>();

        List<CollarDto> collars = collarService.getCollarByCollarName(collarName, page, limit);

        for (CollarDto collarDto : collars) {
            CollarRest collarModel = new CollarRest();
            BeanUtils.copyProperties(collarDto, collarModel);
            returnValue.add(collarModel);
        }

        return returnValue;
    }

}
