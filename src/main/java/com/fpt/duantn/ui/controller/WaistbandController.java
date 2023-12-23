package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.WaistbandService;
import com.fpt.duantn.shrared.dto.CRUD.WaistbandDto;
import com.fpt.duantn.ui.model.request.WaistbandRequest;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.WaistbandRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
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
@RequestMapping("/waistband")
public class WaistbandController {

    @Autowired
    WaistbandService waistbandService;

    @GetMapping(path = "/{id}")
    public WaistbandRest getWaistband(@PathVariable Long id) {
        WaistbandRest returnValue = new WaistbandRest();

        WaistbandDto waistbandDto = waistbandService.getWaistbandById(id);
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
    public PaginationRest getWaistbands(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "2") int limit,
                                        @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<WaistbandRest> returnValue = new ArrayList<>();

        List<WaistbandDto> waistbands = waistbandService.getWaistbands(page, limit, filter);

        for (WaistbandDto waistbandDto : waistbands) {
            WaistbandRest waistbandModel = new WaistbandRest();
            BeanUtils.copyProperties(waistbandDto, waistbandModel);
            returnValue.add(waistbandModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListWaistbands(returnValue);
        paginationRest.setTotal(waistbandService.count(filter));

        return paginationRest;
    }


    @PutMapping(path = "/{id}")
    public WaistbandRest updateWaistband(@PathVariable Long id, @RequestBody WaistbandRequest waistbandDetails) {
        WaistbandRest returnValue = new WaistbandRest();

        WaistbandDto waistbandDto = new WaistbandDto();
        waistbandDto = new ModelMapper().map(waistbandDetails, WaistbandDto.class);

        WaistbandDto updateWaistband = waistbandService.updateWaistband(id, waistbandDto);
        returnValue = new ModelMapper().map(updateWaistband, WaistbandRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteWaistband(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            waistbandService.deleteWaistband(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Cap Quan: Cap Quan có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Cap Quan: " + e.getMessage());
        }return returnValue;
    }
}
