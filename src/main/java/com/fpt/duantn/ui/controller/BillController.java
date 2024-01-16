package com.fpt.duantn.ui.controller;

import com.fpt.duantn.io.entity.*;
import com.fpt.duantn.services.BillService;
import com.fpt.duantn.services.impl.*;
import com.fpt.duantn.shrared.dto.CRUD.BillDto;
import com.fpt.duantn.shrared.dto.CRUD.BrandDto;
import com.fpt.duantn.ui.model.request.BillRequest;
import com.fpt.duantn.ui.model.response.*;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    BillService billService;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    ProductDetailServiceImpl productDetailService;

    @Autowired
    BillDetailServiceImpl billDetailService;

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

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @GetMapping("/sellon")
    @ResponseBody
    public PaginationRest getBillSellOn(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "limit", defaultValue = "5") int limit,
                                        @RequestParam(value = "search", defaultValue = "") String search,
                                        @RequestParam(value = "status", defaultValue = "-1") int status,
            HttpServletRequest request, Authentication authentication
    ) {
        User user = userService.findByUsername(authentication.getName()).orElse(null);
        List<BillRest> returnValue = new ArrayList<>();

        List<BillDto> bills = billService.getBills(page, limit, search,status,user.getId());

        for (BillDto billDto : bills) {
            BillRest billModel = new BillRest();
            BeanUtils.copyProperties(billDto, billModel);
            returnValue.add(billModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListBill(returnValue);
        paginationRest.setTotal(billService.count(search,status,user.getId()));

        return paginationRest;

    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PutMapping("/cancel-bill/{id}")
    public ResponseEntity huyBill(@PathVariable Long id, Authentication authentication) {
        if (billService.existsById(id)){
            BillEntity bill = billService.findById(id).get();
            User user = userService.findByUsername(authentication.getName()).orElse(null);
            CustomerEntity customer  =  customerService.findById(user.getId()).orElse(null);
            if (customer==null){
                return ResponseEntity.badRequest().body("Không lấy được thông tin đăng nhập");
            }
            if (user.getId().toString().equals(bill.getCustomer().getId())){
                if (bill.getStatus()==1){
                    if (bill.getPaymentAmount()==null||bill.getPaymentAmount().doubleValue()==0){

                        bill.setStatus(0);
                        billService.save(bill);
                        return ResponseEntity.ok(("Hủy đơn thành công"));
                    }else {
                        return ResponseEntity.badRequest().body("Đơn hàng đã thanh toán không thể hủy");
                    }
                }else if (bill.getStatus()==-2){
                    if (bill.getPaymentAmount()==null||bill.getPaymentAmount().doubleValue()==0){
                        List<BillDetailEntity> billDetails = billDetailService.findByBillIdAndStatus(bill.getId(),1);
                        List<ProductDetailEntity> productDetails2  = new ArrayList<>();
                        for (BillDetailEntity billDetail:billDetails) {
                            ProductDetailEntity productDetail = billDetail.getProductDetail();
                            productDetail.setQuantity(productDetail.getQuantity()+billDetail.getQuantity());
                            productDetails2.add(productDetail);
                        }

                        bill.setStatus(0);
                        productDetailService.saveAll(productDetails2);
                        billService.save(bill);
                        return ResponseEntity.ok(("Hủy đơn thành công"));
                    }else {
                        return ResponseEntity.badRequest().body("Đơn hàng đã thanh toán không thể hủy");
                    }
                } else {
                    return ResponseEntity.badRequest().body("Chỉ có thể hủy khi đơn Chờ xử lí hoặc chờ thanh toán !");
                }
            }else {
                return ResponseEntity.badRequest().body("Bạn không có quyền hủy đơn này");
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PutMapping("/nhan-hang/{id}")
    public ResponseEntity nhanHang(@PathVariable Long id,Authentication authentication) {
        if (billService.existsById(id)){
            BillEntity bill = billService.findById(id).get();
            User user = userService.findByUsername(authentication.getName()).orElse(null);
            CustomerEntity customer  =  customerService.findById(user.getId()).orElse(null);
            if (customer==null){
                return ResponseEntity.badRequest().body("Không lấy được thông tin đăng nhập");
            }
            if (user.getId().toString().equals(bill.getCustomer().getId())){
                if (bill.getStatus()==5){

                    bill.setStatus(6);
                    billService.save(bill);
                    return ResponseEntity.ok(("Đã nhận hàng thành công"));

                } else {
                    return ResponseEntity.badRequest().body("Chỉ có thể hủy khi đơn Chờ xử lí hoặc chờ thanh toán !");
                }
            }else {
                return ResponseEntity.badRequest().body("Bạn không có quyền hủy đơn này");
            }

        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
}
