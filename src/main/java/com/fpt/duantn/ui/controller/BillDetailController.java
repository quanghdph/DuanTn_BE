package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.BillDetailService;
import com.fpt.duantn.shrared.dto.CRUD.BillDetailDto;
import com.fpt.duantn.ui.model.request.BillDetailRequest;
import com.fpt.duantn.ui.model.response.BillDetailRest;
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
@RequestMapping("/bill-detail")
public class BillDetailController {

    @Autowired
    BillDetailService billDetailService;

    @GetMapping(path = "/{id}")
    public BillDetailRest getBillDetail(@PathVariable Long id) {
        BillDetailRest returnValue = new BillDetailRest();

        BillDetailDto billDetailDto = billDetailService.getBillDetailById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(billDetailDto, BillDetailRest.class);

        return returnValue;
    }

    @PostMapping()
    public BillDetailRest createBillDetail(@RequestBody BillDetailRequest billDetailDetails) throws Exception {
        BillDetailRest returnValue = new BillDetailRest();

        ModelMapper modelMapper = new ModelMapper();
        BillDetailDto billDetailDto = modelMapper.map(billDetailDetails, BillDetailDto.class);

        billDetailDto.setProductDetail(billDetailDetails.getProductDetail());
        billDetailDto.setBill(billDetailDetails.getBill());

        BillDetailDto createdUser = billDetailService.createBillDetail(billDetailDto);
        returnValue = modelMapper.map(createdUser, BillDetailRest.class);

        return returnValue;
    }



    @PutMapping(path = "/{id}")
    public BillDetailRest updateBillDetail(@PathVariable Long id, @RequestBody BillDetailRequest billDetailDetails) {
        BillDetailRest returnValue = new BillDetailRest();

        BillDetailDto billDetailDto = new BillDetailDto();
        billDetailDto = new ModelMapper().map(billDetailDetails, BillDetailDto.class);

        billDetailDto.setProductDetail(billDetailDetails.getProductDetail());
        billDetailDto.setBill(billDetailDetails.getBill());

        BillDetailDto updateBillDetail = billDetailService.updateBillDetail(id, billDetailDto);
        returnValue = new ModelMapper().map(updateBillDetail, BillDetailRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteBillDetail(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            billDetailService.deleteBillDetail(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Chi Tiet Hoa Don: Chi Tiet Hoa Don có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Chi Tiet Hoa Don: " + e.getMessage());
        }
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


    @GetMapping()
    public PaginationRest getBillDetails(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "limit", defaultValue = "5") int limit,
                                         @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<BillDetailRest> returnValue = new ArrayList<>();

        List<BillDetailDto> billDetails = billDetailService.getBillDetails(page, limit, filter);

        for (BillDetailDto billDetailDto : billDetails) {
            BillDetailRest billDetailModel = new BillDetailRest();
            BeanUtils.copyProperties(billDetailDto, billDetailModel);
            returnValue.add(billDetailModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListBillDetails(returnValue);
        paginationRest.setTotal(billDetailService.count(filter));

        return paginationRest;
    }

}
