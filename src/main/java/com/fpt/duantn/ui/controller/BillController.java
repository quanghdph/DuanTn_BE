package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.BillService;
import com.fpt.duantn.shrared.dto.CRUD.BillDto;
import com.fpt.duantn.ui.model.request.BillDetailsRequestModel;
import com.fpt.duantn.ui.model.response.BillRest;
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
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping(path = "/{id}")
    public BillRest getBill(@PathVariable String id) {
        BillRest returnValue = new BillRest();

        BillDto billDto = billService.getBillByBillCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(billDto, BillRest.class);

        return returnValue;
    }

    @PostMapping()
    public BillRest createBill(@RequestBody BillDetailsRequestModel billDetails) throws Exception {
        BillRest returnValue = new BillRest();

        ModelMapper modelMapper = new ModelMapper();
        BillDto billDto = modelMapper.map(billDetails, BillDto.class);

        BillDto createdUser = billService.createBill(billDto);
        returnValue = modelMapper.map(createdUser, BillRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<BillRest> getBills(@RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<BillRest> returnValue = new ArrayList<>();

        List<BillDto> bills = billService.getBills(page, limit);

        for (BillDto billDto : bills) {
            BillRest billModel = new BillRest();
            BeanUtils.copyProperties(billDto, billModel);
            returnValue.add(billModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public BillRest updateBill(@PathVariable String id, @RequestBody BillDetailsRequestModel billDetails) {
        BillRest returnValue = new BillRest();

        BillDto billDto = new BillDto();
        billDto = new ModelMapper().map(billDetails, BillDto.class);

        BillDto updateBill = billService.updateBill(id, billDto);
        returnValue = new ModelMapper().map(updateBill, BillRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteBill(@PathVariable String id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        billService.deleteBill(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }



    @GetMapping("/search")
    public List<BillRest> searchBills(@RequestParam(value = "billName") String billName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<BillRest> returnValue = new ArrayList<>();

        List<BillDto> bills = billService.getBillByBillName(billName, page, limit);

        for (BillDto billDto : bills) {
            BillRest billModel = new BillRest();
            BeanUtils.copyProperties(billDto, billModel);
            returnValue.add(billModel);
        }

        return returnValue;
    }

}
