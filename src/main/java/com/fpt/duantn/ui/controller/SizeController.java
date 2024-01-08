package com.fpt.duantn.ui.controller;


import com.fpt.duantn.services.SizeService;
import com.fpt.duantn.shrared.dto.CRUD.SizeDto;
import com.fpt.duantn.ui.model.request.SizeRequest;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.SizeRest;
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
@RequestMapping("/size")
public class SizeController {

    @Autowired
    SizeService sizeService;



    @GetMapping(path = "/{id}")
    public SizeRest getSize(@PathVariable Long id) {
        SizeRest returnValue = new SizeRest();

        SizeDto sizeDto = sizeService.getSizeById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(sizeDto, SizeRest.class);

        return returnValue;
    }


    @PostMapping()
    public SizeRest createSize(@RequestBody SizeRequest sizeDetails) throws Exception {
        SizeRest returnValue = new SizeRest();

        ModelMapper modelMapper = new ModelMapper();
        SizeDto sizeDto = modelMapper.map(sizeDetails, SizeDto.class);

        SizeDto createdUser = sizeService.createSize(sizeDto);
        returnValue = modelMapper.map(createdUser, SizeRest.class);

        return returnValue;
    }


    @GetMapping()
    public PaginationRest getSizes(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "10") int limit,
                                   @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<SizeRest> returnValue = new ArrayList<>();

        List<SizeDto> sizes = sizeService.getSizes(page, limit, filter);

        for (SizeDto sizeDto : sizes) {
            SizeRest sizeModel = new SizeRest();
            BeanUtils.copyProperties(sizeDto, sizeModel);
            returnValue.add(sizeModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListSizes(returnValue);
        paginationRest.setTotal(sizeService.count(filter));

        return paginationRest;
    }


    @PutMapping(path = "/{id}")
    public SizeRest updateSize(@PathVariable Long id, @RequestBody SizeRequest sizeDetails) {
        SizeRest returnValue = new SizeRest();

        SizeDto sizeDto = new SizeDto();
        sizeDto = new ModelMapper().map(sizeDetails, SizeDto.class);

        SizeDto updateSize = sizeService.updateSize(id, sizeDto);
        returnValue = new ModelMapper().map(updateSize, SizeRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteSize(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            sizeService.deleteSize(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Kich Co: Kich Co có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Kich Co: " + e.getMessage());
        }return returnValue;
    }
}
