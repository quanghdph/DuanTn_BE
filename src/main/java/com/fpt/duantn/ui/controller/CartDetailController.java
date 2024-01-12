package com.fpt.duantn.ui.controller;

import com.fpt.duantn.io.entity.CartDetailEntity;
import com.fpt.duantn.io.entity.CustomerEntity;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.entity.User;
import com.fpt.duantn.io.repository.CartDetailRepository;
import com.fpt.duantn.io.repository.ProductDetailRepository;
import com.fpt.duantn.security.JwtAuthenticationFilter;
import com.fpt.duantn.services.CartDetailService;
import com.fpt.duantn.services.CustomerService;
import com.fpt.duantn.services.impl.UserServiceImpl;
import com.fpt.duantn.shrared.dto.CRUD.CartDetailDto;
import com.fpt.duantn.ui.model.request.CartDetailRequest;
import com.fpt.duantn.ui.model.response.CartDetailRest;
import com.fpt.duantn.ui.model.response.OperationStatusModel;
import com.fpt.duantn.ui.model.response.PaginationRest;
import com.fpt.duantn.ui.model.response.RequestOperationStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = {"http://localhost:4201","http://localhost:4200"})
@RestController
@RequestMapping("/cart-detail")
public class CartDetailController {

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/cart-detail/{id}")
    public ResponseEntity<CartDetailEntity> getByCartDetail(@PathVariable("id") Long id) {
        if (!cartDetailRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        CartDetailEntity cartDetail = cartDetailRepository.findById(id).get();
        cartDetail.setCustomer(null);
        return ResponseEntity.ok(cartDetail);
    }

    @RequestMapping(value = "/cart-details", method = RequestMethod.GET)
    public ResponseEntity<?> getCartDetails(Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElse(null);

        if (!customerService.existsById(user.getId())) {
            return ResponseEntity.badRequest().body("Cần đăng nhập đúng tài khoản khách hàng");
        }
        List<CartDetailEntity> cartDetails = cartDetailRepository.findByCustomerIdAndStatus(user.getId(),1);
        for (CartDetailEntity cartDetail:cartDetails) {
            cartDetail.setCustomer(null);
        }
        return ResponseEntity.ok(cartDetails);
    }



    @PostMapping()
    public ResponseEntity<?> post(@RequestParam Long productDetailId,Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElse(null);
        if (!customerService.existsById(user.getId())) {
            return ResponseEntity.badRequest().body("Cần đăng nhập đúng tài khoản khách hàng");
        }
        if (cartDetailRepository.existsByCustomerIdAndProductDetailIdAndStatus(user.getId(),productDetailId,1)) {
            return ResponseEntity.badRequest().body("Sản phẩm đã có trong gio hàng");
        }

        CartDetailEntity cartDetail = new CartDetailEntity();
        ProductDetailEntity productDetail = new ProductDetailEntity();
        productDetail.setId(productDetailId);
        cartDetail.setProductDetail(productDetail);

        CustomerEntity customer = new CustomerEntity();
        customer.setId(user.getId());
        cartDetail.setCustomer(customer);

        cartDetail.setStatus(1);
        CartDetailEntity cartDetailSaved = cartDetailRepository.save(cartDetail);
        cartDetailSaved.setCustomer(null);

        return ResponseEntity.ok(cartDetailSaved);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id,Authentication authentication) {
        User user = userService.findByUsername(authentication.getName()).orElse(null);
        if (!customerService.existsById(user.getId())) {
            return ResponseEntity.badRequest().body("Cần đăng nhập đúng tài khoản khách hàng");
        }
        if (!cartDetailRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        int result = cartDetailRepository.deleteByProductDetailIdAndCustomerId(id,user.getId());
        if (result>0){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().body("Xóa không thành công");
        }

    }

}
