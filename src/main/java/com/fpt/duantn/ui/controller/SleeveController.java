package com.fpt.duantn.ui.controller;


import com.fpt.duantn.services.SleeveService;
import com.fpt.duantn.shrared.dto.CRUD.SleeveDto;
import com.fpt.duantn.ui.model.request.SleeveDetailsRequestModel;
import com.fpt.duantn.ui.model.response.SleeveRest;
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
@RequestMapping("/sleeve")
public class SleeveController {

    @Autowired
    SleeveService sleeveService;



    @GetMapping(path = "/{id}")
    public SleeveRest getSleeve(@PathVariable String id) {
        SleeveRest returnValue = new SleeveRest();

        SleeveDto sleeveDto = sleeveService.getSleeveBySleeveCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(sleeveDto, SleeveRest.class);

        return returnValue;
    }


    @PostMapping()
    public SleeveRest createSleeve(@RequestBody SleeveDetailsRequestModel sleeveDetails) throws Exception {
        SleeveRest returnValue = new SleeveRest();

        ModelMapper modelMapper = new ModelMapper();
        SleeveDto sleeveDto = modelMapper.map(sleeveDetails, SleeveDto.class);

        SleeveDto createdUser = sleeveService.createSleeve(sleeveDto);
        returnValue = modelMapper.map(createdUser, SleeveRest.class);

        return returnValue;
    }


    @GetMapping()
    public List<SleeveRest> getSleeves(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<SleeveRest> returnValue = new ArrayList<>();

        List<SleeveDto> sleeves = sleeveService.getSleeves(page, limit);

        for (SleeveDto sleeveDto : sleeves) {
            SleeveRest sleeveModel = new SleeveRest();
            BeanUtils.copyProperties(sleeveDto, sleeveModel);
            returnValue.add(sleeveModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public SleeveRest updateSleeve(@PathVariable String id, @RequestBody SleeveDetailsRequestModel sleeveDetails) {
        SleeveRest returnValue = new SleeveRest();

        SleeveDto sleeveDto = new SleeveDto();
        sleeveDto = new ModelMapper().map(sleeveDetails, SleeveDto.class);

        SleeveDto updateSleeve = sleeveService.updateSleeve(id, sleeveDto);
        returnValue = new ModelMapper().map(updateSleeve, SleeveRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteSleeve(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        sleeveService.deleteSleeve(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<SleeveRest> searchSleeves(@RequestParam(value = "sleeveName") String sleeveName,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<SleeveRest> returnValue = new ArrayList<>();

        List<SleeveDto> sleeves = sleeveService.getSleeveBySleeveName(sleeveName, page, limit);

        for (SleeveDto sleeveDto : sleeves) {
            SleeveRest sleeveModel = new SleeveRest();
            BeanUtils.copyProperties(sleeveDto, sleeveModel);
            returnValue.add(sleeveModel);
        }

        return returnValue;
    }


}
