package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.WaistbandService;
import com.fpt.duantn.shrared.dto.CRUD.WaistbandDto;
import com.fpt.duantn.ui.model.request.WaistbandRequest;
import com.fpt.duantn.ui.model.response.WaistbandRest;
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
@RequestMapping("/api/waistband")
public class WaistbandController {

    @Autowired
    WaistbandService waistbandService;

    @GetMapping(path = "/{id}")
    public WaistbandRest getWaistband(@PathVariable String id) {
        WaistbandRest returnValue = new WaistbandRest();

        WaistbandDto waistbandDto = waistbandService.getWaistbandByWaistbandCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(waistbandDto, WaistbandRest.class);

        return returnValue;
    }

    @PostMapping()
    public WaistbandRest createWaistband(@RequestBody WaistbandRequest waistbandDetails) throws Exception {
        WaistbandRest returnValue = new WaistbandRest();

        ModelMapper modelMapper = new ModelMapper();
        WaistbandDto waistbandDto = modelMapper.map(waistbandDetails, WaistbandDto.class);

        WaistbandDto createdUser = waistbandService.createWaistband(waistbandDto);
        returnValue = modelMapper.map(createdUser, WaistbandRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<WaistbandRest> getWaistbands(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<WaistbandRest> returnValue = new ArrayList<>();

        List<WaistbandDto> waistbands = waistbandService.getWaistbands(page, limit);

        for (WaistbandDto waistbandDto : waistbands) {
            WaistbandRest waistbandModel = new WaistbandRest();
            BeanUtils.copyProperties(waistbandDto, waistbandModel);
            returnValue.add(waistbandModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public WaistbandRest updateWaistband(@PathVariable String id, @RequestBody WaistbandRequest waistbandDetails) {
        WaistbandRest returnValue = new WaistbandRest();

        WaistbandDto waistbandDto = new WaistbandDto();
        waistbandDto = new ModelMapper().map(waistbandDetails, WaistbandDto.class);

        WaistbandDto updateWaistband = waistbandService.updateWaistband(id, waistbandDto);
        returnValue = new ModelMapper().map(updateWaistband, WaistbandRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteWaistband(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        waistbandService.deleteWaistband(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<WaistbandRest> searchWaistbands(@RequestParam(value = "waistbandName") String waistbandName,
                                          @RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<WaistbandRest> returnValue = new ArrayList<>();

        List<WaistbandDto> waistbands = waistbandService.getWaistbandByWaistbandName(waistbandName, page, limit);

        for (WaistbandDto waistbandDto : waistbands) {
            WaistbandRest waistbandModel = new WaistbandRest();
            BeanUtils.copyProperties(waistbandDto, waistbandModel);
            returnValue.add(waistbandModel);
        }

        return returnValue;
    }

}
