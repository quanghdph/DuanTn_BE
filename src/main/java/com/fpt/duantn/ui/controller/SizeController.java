package com.fpt.duantn.ui.controller;


import com.fpt.duantn.services.SizeService;
import com.fpt.duantn.shrared.dto.CRUD.SizeDto;
import com.fpt.duantn.ui.model.request.SizeDetailsRequestModel;
import com.fpt.duantn.ui.model.response.SizeRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/size")
public class SizeController {

    @Autowired
    SizeService sizeService;



    @GetMapping(path = "/{id}")
    public SizeRest getSize(@PathVariable String id) {
        SizeRest returnValue = new SizeRest();

        SizeDto sizeDto = sizeService.getSizeBySizeCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(sizeDto, SizeRest.class);

        return returnValue;
    }


    @PostMapping()
    public SizeRest createSize(@RequestBody SizeDetailsRequestModel sizeDetails) throws Exception {
        SizeRest returnValue = new SizeRest();

        ModelMapper modelMapper = new ModelMapper();
        SizeDto sizeDto = modelMapper.map(sizeDetails, SizeDto.class);

        SizeDto createdUser = sizeService.createSize(sizeDto);
        returnValue = modelMapper.map(createdUser, SizeRest.class);

        return returnValue;
    }


    @GetMapping()
    public List<SizeRest> getSizes(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<SizeRest> returnValue = new ArrayList<>();

        List<SizeDto> sizes = sizeService.getSizes(page, limit);

        for (SizeDto sizeDto : sizes) {
            SizeRest sizeModel = new SizeRest();
            BeanUtils.copyProperties(sizeDto, sizeModel);
            returnValue.add(sizeModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public SizeRest updateSize(@PathVariable String id, @RequestBody SizeDetailsRequestModel sizeDetails) {
        SizeRest returnValue = new SizeRest();

        SizeDto sizeDto = new SizeDto();
        sizeDto = new ModelMapper().map(sizeDetails, SizeDto.class);

        SizeDto updateSize = sizeService.updateSize(id, sizeDto);
        returnValue = new ModelMapper().map(updateSize, SizeRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteSize(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        sizeService.deleteSize(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<SizeRest> searchSizes(@RequestParam(value = "sizeName") String sizeName,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<SizeRest> returnValue = new ArrayList<>();

        List<SizeDto> sizes = sizeService.getSizeBySizeName(sizeName, page, limit);

        for (SizeDto sizeDto : sizes) {
            SizeRest sizeModel = new SizeRest();
            BeanUtils.copyProperties(sizeDto, sizeModel);
            returnValue.add(sizeModel);
        }

        return returnValue;
    }


}
