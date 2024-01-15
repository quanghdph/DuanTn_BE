package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.BillService;
import com.fpt.duantn.shrared.dto.CRUD.BillDto;
import com.fpt.duantn.shrared.dto.CRUD.BrandDto;
import com.fpt.duantn.ui.model.request.BillRequest;
import com.fpt.duantn.ui.model.response.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;

    @GetMapping(path = "/{id}")
    public BillRest getBill(@PathVariable Long id) {
        BillRest returnValue = new BillRest();

        BillDto billDto = billService.getBillById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(billDto, BillRest.class);

        return returnValue;
    }

    @PostMapping()
    public BillRest createBill(@RequestBody BillRequest billDetails) throws Exception {
        BillRest returnValue = new BillRest();

        ModelMapper modelMapper = new ModelMapper();
        BillDto billDto = modelMapper.map(billDetails, BillDto.class);

        billDto.setCustomer(billDetails.getCustomer());
        billDto.setEmployee(billDetails.getEmployee());

        BillDto createdUser = billService.createBill(billDto);
        returnValue = modelMapper.map(createdUser, BillRest.class);

        return returnValue;
    }




    @PutMapping(path = "/{id}")
    public BillRest updateBill(@PathVariable Long id, @RequestBody BillRequest billDetails) {
        BillRest returnValue = new BillRest();

        BillDto billDto = new BillDto();
        billDto = new ModelMapper().map(billDetails, BillDto.class);

        billDto.setEmployee(billDetails.getEmployee());
        billDto.setCustomer(billDetails.getCustomer());

        BillDto updateBill = billService.updateBill(id, billDto);
        returnValue = new ModelMapper().map(updateBill, BillRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteBill(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            billService.deleteBill(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Hoa Don: Hoa Don có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Hoa Don: " + e.getMessage());
        }return returnValue;
    }



//    @GetMapping("/search")
//    public List<BillRest> searchBills(@RequestParam(value = "billName") String billName,
//                                              @RequestParam(value = "page", defaultValue = "0") int page,
//                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
//        List<BillRest> returnValue = new ArrayList<>();
//
//        List<BillDto> bills = billService.getBillByBillName(billName, page, limit);
//
//        for (BillDto billDto : bills) {
//            BillRest billModel = new BillRest();
//            BeanUtils.copyProperties(billDto, billModel);
//            returnValue.add(billModel);
//        }
//
//        return returnValue;
//    }

    @GetMapping()
    public PaginationRest getBills(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "5") int limit,
                                   @RequestParam(value = "search", defaultValue = "") String search,
                                   @RequestParam(value = "status", defaultValue = "-1") int status) {
        List<BillRest> returnValue = new ArrayList<>();

        List<BillDto> bills = billService.getBills(page, limit, search,status);

        for (BillDto billDto : bills) {
            BillRest billModel = new BillRest();
            BeanUtils.copyProperties(billDto, billModel);
            returnValue.add(billModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListBill(returnValue);
        paginationRest.setTotal(billService.count(search,status));

        return paginationRest;
    }

//    @GetMapping("/search")
//    public List<BrandRest> searchBill(@RequestParam(value = "brandName") String brandName,
//                                        @RequestParam(value = "page", defaultValue = "0") int page,
//                                        @RequestParam(value = "limit", defaultValue = "5") int limit) {
//        List<BrandRest> returnValue = new ArrayList<>();
//
//        List<BrandDto> brands = brandService.getBrandByBrandName(brandName, page, limit);
//
//        for (BrandDto brandDto : brands) {
//            BrandRest brandModel = new BrandRest();
//            BeanUtils.copyProperties(brandDto, brandModel);
//            returnValue.add(brandModel);
//        }
//
//        return returnValue;
//    }
}
