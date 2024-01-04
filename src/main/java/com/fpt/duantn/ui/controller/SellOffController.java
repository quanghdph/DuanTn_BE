package com.fpt.duantn.ui.controller;

import com.fpt.duantn.io.entity.*;
import com.fpt.duantn.services.*;
import com.fpt.duantn.services.impl.UserServiceImpl;
import com.fpt.duantn.ui.model.request.SellOfProductRequest;
import com.fpt.duantn.ui.model.request.SellOfRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/selloff")

public class SellOffController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BillService billService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private BillDetailService billDetailService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    UserServiceImpl userService;
//    @Autowired
//    private Authenti authenticationService;

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @PostMapping("/calculate-money")
    public ResponseEntity<?> calculateMoney(@ModelAttribute() SellOfRequest sellOffRequest){
        List<SellOfProductRequest> sellOffProductRequests= sellOffRequest.getSanPhams();
        if (sellOffRequest==null){
            return ResponseEntity.ok(0D);
        }
        if (sellOffProductRequests==null){
            return ResponseEntity.ok(0D);
        }
        if (sellOffProductRequests.size()<=0){
            return ResponseEntity.ok(0D);
        }
        Double sum =0D;
        for (SellOfProductRequest request : sellOffProductRequests){
            ProductDetailEntity productDetail =  productDetailService.findById(request.getId()).orElse(null);
            if (request.getId()==null||request.getQuantity()==null){
                return ResponseEntity.badRequest().body("Thông tin sản phẩm hoặc số lượng bị thiếu");
            }
            if (productDetail == null){
                return ResponseEntity.badRequest().body("Sản phẩm "+request.getId()+" không tồn tại hoặc đã ngừng kinh doanh");
            }
            sum+=request.getQuantity()*productDetail.getPrice().doubleValue();
        }
        return ResponseEntity.ok(sum);
    }

    @GetMapping ("/calculate-money/{billID}")
    public ResponseEntity<?> calculateMoney(@PathVariable Optional<Long> billID){
        if (!billService.existsById(billID.orElse(null))){
            return ResponseEntity.badRequest().body("Hóa đơn "+billID.orElse(null)+" không tồn tại");
        }
        Optional<Double> sumMoney = billDetailService.sumMoneyByBillIdAndType(billID.orElse(null),null);
        return ResponseEntity.ok(sumMoney.orElse(null));
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE','CUSTOMER')")
    @PostMapping ()
    public ResponseEntity<?> add(@RequestBody() SellOfRequest sellOffRequest, Authentication authentication) {
        if (sellOffRequest.getThanhToan()==null||sellOffRequest.getTrangThaiTT()==null||sellOffRequest.getSanPhams()==null){
            return ResponseEntity.badRequest().body("Thông tin Không đầy đủ");
        }
        CustomerEntity customer =null;
        if (sellOffRequest.getIdKhachHang()!=null){
            customer = customerService.findById(sellOffRequest.getIdKhachHang()).orElse(null);
        }

        List<SellOfProductRequest>sellOffProductRequests = sellOffRequest.getSanPhams();
        if (sellOffProductRequests.size()<=0){
            return ResponseEntity.badRequest().body("Đơn hàng trống !");
        }
        List<ProductDetailEntity> productDetails = new ArrayList<>();
        List<BillDetailEntity> billDetails = new ArrayList<>();
        Double sum =0D;
        for (SellOfProductRequest request : sellOffProductRequests){
            if (request.getId()==null||request.getQuantity()==null){
                return ResponseEntity.badRequest().body("Thông tin sản phẩm hoặc số lượng bị thiếu");
            }
            ProductDetailEntity productDetail =  productDetailService.findById(request.getId()).orElse(null);
            if (productDetail == null){
                return ResponseEntity.badRequest().body("Sản phẩm "+request.getId()+" không tồn tại ");
            }
            if (productDetail.getQuantity()<request.getQuantity()){
                return ResponseEntity.badRequest().body("Sản phẩm "+request.getId()+" không đủ số lượng");
            }
            if (request.getQuantity()<=0){
                return ResponseEntity.badRequest().body("Sản phẩm "+request.getId()+" số lượng phải lớn hơn 0");
            }
            BillDetailEntity billDetail = new BillDetailEntity();
            billDetail.setProductDetail(productDetail);
            billDetail.setPrice(productDetail.getPrice());
            billDetail.setQuantity(request.getQuantity());
            billDetail.setStatus(1);
            productDetail.setQuantity(productDetail.getQuantity()- billDetail.getQuantity());
            productDetails.add(productDetail);
            billDetails.add(billDetail);
            sum+=request.getQuantity()*productDetail.getPrice().doubleValue();
        }
        BillEntity newBill = new BillEntity();
        User user = userService.findByUsername(authentication.getName()).orElse(null);

        newBill.setEmployee(EmployeeEntity.builder().id(user.getId()).build());

        if (sellOffRequest.getThanhToan().equals(1)){
            newBill.setStatus(-2);
        }else {
            newBill.setStatus(7);
        }
        newBill.setCustomer(customer);
        if (customer!=null){
            newBill.setPhoneNumber(customer.getPhoneNumber());
        }


        BillEntity newBillSaved = null;
        try {
            newBillSaved = billService.save(newBill);
            for (BillDetailEntity billDetail : billDetails){
                billDetail.setBill(newBillSaved);
            }
            productDetailService.saveAll(productDetails);
            billDetailService.saveAll(billDetails);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.ok("Lỗi , Kiểm tra lại hóa đơn");
        }
        return ResponseEntity.ok(newBillSaved.getId());
    }
}
