package com.fpt.duantn.ui.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.duantn.io.entity.*;
import com.fpt.duantn.io.repository.CartDetailRepository;
import com.fpt.duantn.services.BillDetailService;
import com.fpt.duantn.services.BillService;
import com.fpt.duantn.services.CustomerService;
import com.fpt.duantn.services.ProductDetailService;
import com.fpt.duantn.services.impl.GiaoHangTietKiemServiceImpl;
import com.fpt.duantn.services.impl.UserServiceImpl;
import com.fpt.duantn.ui.model.request.SellOfProductRequest;
import com.fpt.duantn.ui.model.request.SellOnRequest;
import com.fpt.duantn.util.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/sellon")
public class SellOnController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BillService billService;
    @Autowired
    private BillDetailService billDetailService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private GiaoHangTietKiemServiceImpl giaoHangNhanhService;

    @Autowired
    private SendMailUtil sendMailUtil;
    @Autowired
    private CartDetailRepository cartdetailRepository;

    @PostMapping("/calculate-money")
    public ResponseEntity<?> calculateMoney(@RequestBody SellOnRequest sellOnRequest){
        List<SellOfProductRequest> sellOffProductRequests= sellOnRequest.getSanPhams();
        Double sum =0D;
        if (sellOffProductRequests.size()>0){
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

        }
        return ResponseEntity.ok(sum);

    }

    @GetMapping("/calculate-money/{billID}")
    public ResponseEntity<?> calculateMoney(@PathVariable Optional<Long> billID){
        if (!billService.existsById(billID.orElse(null))){
            return ResponseEntity.badRequest().body("Hóa đơn "+billID.orElse(null)+" không tồn tại");
        }
        Optional<Double> sumMoney = billDetailService.sumMoneyByBillIdAndType(billID.orElse(null),null);
        return ResponseEntity.ok(sumMoney.orElse(null));
    }


    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping ()
    public ResponseEntity<?> add(@RequestBody() SellOnRequest sellOnRequest, Authentication authentication) {

        if (sellOnRequest.getPaymentType()==null||sellOnRequest.getAddress()==null||sellOnRequest.getCity()==null||sellOnRequest.getDistrict()==null||sellOnRequest.getWard()==null||sellOnRequest.getNote()==null||sellOnRequest.getPhoneNumber()==null||sellOnRequest.getSanPhams()==null) {
            return ResponseEntity.badRequest().body("Thông tin Không đầy đủ");
        }
        List<SellOfProductRequest>sellOffProductRequests = sellOnRequest.getSanPhams();
        if (sellOffProductRequests.size()<=0){
            return ResponseEntity.badRequest().body("Đơn hàng trống !");
        }

        List<BillDetailEntity> billDetails = new ArrayList<>();
        Double sum =0D;
        Integer sumQ =0;
        for (SellOfProductRequest request : sellOffProductRequests){
            if (request.getId()==null||request.getQuantity()==null){
                return ResponseEntity.badRequest().body("Thông tin sản phẩm hoặc số lượng bị thiếu");
            }
            ProductDetailEntity productDetail =  productDetailService.findById(request.getId()).orElse(null);
            if (productDetail == null||productDetail.getStatus().equals(0)||productDetail.getProduct().getStatus().equals(0)){
                return ResponseEntity.badRequest().body("Sản phẩm "+request.getId()+" không tồn tại hoặc đã ngừng kinh doanh");
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
            billDetails.add(billDetail);
            if (sellOnRequest.getPaymentType().equals(2)){
                productDetail.setQuantity(productDetail.getQuantity()- billDetail.getQuantity());
            }
            sum+=request.getQuantity()*productDetail.getPrice().doubleValue();
            sumQ+=request.getQuantity();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Double shipFee = 0D;
        try {
            ResponseEntity responseEntity = giaoHangNhanhService.tinhShip(sellOnRequest.getCity(),sellOnRequest.getDistrict(),sellOnRequest.getWard(),sumQ,((long)sum.doubleValue()));
            if (responseEntity.getStatusCode()== HttpStatus.OK){
                JsonNode jsonNode = objectMapper.readTree(responseEntity.getBody().toString());
                shipFee = jsonNode.get("data").get("total").asDouble();
            }else {
                return responseEntity;
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Không thể tính tiên ship ! Kiểm tra lại địa chỉ ");
        }


        BillEntity newBill = new BillEntity();
        User user = userService.findByUsername(authentication.getName()).orElse(null);
        CustomerEntity customer =  customerService.findById(user.getId()).orElse(null);
        newBill.setCustomer(customer);
        newBill.setAddress(sellOnRequest.getAddress());
        newBill.setPhoneNumber(sellOnRequest.getPhoneNumber());
        newBill.setNote(sellOnRequest.getNote());

        if (sellOnRequest.getPaymentType().equals(2)||sellOnRequest.getPaymentType().equals(-2)){
            newBill.setPaymentType(sellOnRequest.getPaymentType());
            if (sellOnRequest.getPaymentType().equals(2)){
                newBill.setStatus(-2);
            }else  if (sellOnRequest.getPaymentType().equals(-2)){
                newBill.setStatus(1);
            }


        }else {
            return ResponseEntity.badRequest().body("Phương thức thanh toán không đúng !");
        }

        BillEntity newBillSaved = null;
        try {
            newBillSaved = billService.save(newBill);
            for (BillDetailEntity billDetail : billDetails){
                billDetail.setBill(newBillSaved);
            }


            billDetailService.saveAll(billDetails);
            for (SellOfProductRequest request : sellOffProductRequests){
                cartdetailRepository.deleteByProductDetailIdAndCustomerId(request.getId(),customer.getId());
            }

        }catch (Exception e){
            return ResponseEntity.badRequest().body("Lỗi , Kiểm tra lại hóa đơn : ");

        }
        return ResponseEntity.ok(newBillSaved.getId());
    }
}
