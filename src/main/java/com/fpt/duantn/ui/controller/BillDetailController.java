package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.BillDetailService;
import com.fpt.duantn.shrared.dto.CRUD.BillDetailDto;
import com.fpt.duantn.ui.model.request.BillDetailDetailsRequestModel;
import com.fpt.duantn.ui.model.response.BillDetailRest;
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
@RequestMapping("/billDetail")
public class BillDetailController {

    @Autowired
    BillDetailService billDetailService;

    @GetMapping(path = "/{id}")
    public BillDetailRest getBillDetail(@PathVariable Long id) {
        BillDetailRest returnValue = new BillDetailRest();

        BillDetailDto billDetailDto = billDetailService.getBillDetailByBillDetailCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(billDetailDto, BillDetailRest.class);

        return returnValue;
    }

    @PostMapping()
    public BillDetailRest createBillDetail(@RequestBody BillDetailDetailsRequestModel billDetailDetails) throws Exception {
        BillDetailRest returnValue = new BillDetailRest();

        ModelMapper modelMapper = new ModelMapper();
        BillDetailDto billDetailDto = modelMapper.map(billDetailDetails, BillDetailDto.class);

        BillDetailDto createdUser = billDetailService.createBillDetail(billDetailDto);
        returnValue = modelMapper.map(createdUser, BillDetailRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<BillDetailRest> getBillDetails(@RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<BillDetailRest> returnValue = new ArrayList<>();

        List<BillDetailDto> billDetails = billDetailService.getBillDetails(page, limit);

        for (BillDetailDto billDetailDto : billDetails) {
            BillDetailRest billDetailModel = new BillDetailRest();
            BeanUtils.copyProperties(billDetailDto, billDetailModel);
            returnValue.add(billDetailModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public BillDetailRest updateBillDetail(@PathVariable Long id, @RequestBody BillDetailDetailsRequestModel billDetailDetails) {
        BillDetailRest returnValue = new BillDetailRest();

        BillDetailDto billDetailDto = new BillDetailDto();
        billDetailDto = new ModelMapper().map(billDetailDetails, BillDetailDto.class);

        BillDetailDto updateBillDetail = billDetailService.updateBillDetail(id, billDetailDto);
        returnValue = new ModelMapper().map(updateBillDetail, BillDetailRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteBillDetail(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        billDetailService.deleteBillDetail(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



//    @GetMapping("/search")
//    public List<BillDetailRest> searchBillDetails(@RequestParam(value = "BillDetailName") String BillDetailName,
//                                              @RequestParam(value = "page", defaultValue = "0") int page,
//                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
//        List<BillDetailRest> returnValue = new ArrayList<>();
//
//        List<BillDetailDto> BillDetails = BillDetailService.getBillDetailByBillDetailName(BillDetailName, page, limit);
//
//        for (BillDetailDto BillDetailDto : BillDetails) {
//            BillDetailRest BillDetailModel = new BillDetailRest();
//            BeanUtils.copyProperties(BillDetailDto, BillDetailModel);
//            returnValue.add(BillDetailModel);
//        }
//
//        return returnValue;
//    }

}
