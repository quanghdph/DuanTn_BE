package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.CartDetailService;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/cart-detail")
public class CartDetailController {

    @Autowired
    CartDetailService cartDetailService;

    @GetMapping(path = "/{id}")
    public CartDetailRest getCartDetail(@PathVariable Long id) {
        CartDetailRest returnValue = new CartDetailRest();

        CartDetailDto cartDetailDto = cartDetailService.getCartDetailById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(cartDetailDto, CartDetailRest.class);

        return returnValue;
    }

    @PostMapping()
    public CartDetailRest createCartDetail(@RequestBody CartDetailRequest cartDetailDetails) throws Exception {
        CartDetailRest returnValue = new CartDetailRest();

        ModelMapper modelMapper = new ModelMapper();
        CartDetailDto cartDetailDto = modelMapper.map(cartDetailDetails, CartDetailDto.class);

        cartDetailDto.setProductDetail(cartDetailDetails.getProductDetail());
        cartDetailDto.setCart(cartDetailDetails.getCart());

        CartDetailDto createdUser = cartDetailService.createCartDetail(cartDetailDto);
        returnValue = modelMapper.map(createdUser, CartDetailRest.class);

        return returnValue;
    }



    @PutMapping(path = "/{id}")
    public CartDetailRest updateCartDetail(@PathVariable Long id, @RequestBody CartDetailRequest cartDetailDetails) {
        CartDetailRest returnValue = new CartDetailRest();

        CartDetailDto cartDetailDto = new CartDetailDto();
        cartDetailDto = new ModelMapper().map(cartDetailDetails, CartDetailDto.class);

        cartDetailDto.setProductDetail(cartDetailDetails.getProductDetail());
        cartDetailDto.setCart(cartDetailDetails.getCart());


        CartDetailDto updateCartDetail = cartDetailService.updateCartDetail(id, cartDetailDto);
        returnValue = new ModelMapper().map(updateCartDetail, CartDetailRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteCartDetail(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            cartDetailService.deleteCartDetail(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Gio Hang Chi Tiet: Gio Hang Chi Tiet có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Gio Hang Chi Tiet: " + e.getMessage());
        }return returnValue;
    }



//    @GetMapping("/search")
//    public List<CartDetailRest> searchCartDetails(@RequestParam(value = "CartDetailName") String CartDetailName,
//                                              @RequestParam(value = "page", defaultValue = "0") int page,
//                                              @RequestParam(value = "limit", defaultValue = "2") int limit) {
//        List<CartDetailRest> returnValue = new ArrayList<>();
//
//        List<CartDetailDto> CartDetails = CartDetailService.getCartDetailByCartDetailName(CartDetailName, page, limit);
//
//        for (CartDetailDto CartDetailDto : CartDetails) {
//            CartDetailRest CartDetailModel = new CartDetailRest();
//            BeanUtils.copyProperties(CartDetailDto, CartDetailModel);
//            returnValue.add(CartDetailModel);
//        }
//
//        return returnValue;
//    }

    @GetMapping()
    public PaginationRest getCartDetails(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "limit", defaultValue = "5") int limit,
                                         @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<CartDetailRest> returnValue = new ArrayList<>();

        List<CartDetailDto> cartDetails = cartDetailService.getCartDetails(page, limit, filter);

        for (CartDetailDto cartDetailDto : cartDetails) {
            CartDetailRest cartDetailModel = new CartDetailRest();
            BeanUtils.copyProperties(cartDetailDto, cartDetailModel);
            returnValue.add(cartDetailModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListCartDetail(returnValue);
        paginationRest.setTotal(cartDetailService.count(filter));

        return paginationRest;
    }


}
