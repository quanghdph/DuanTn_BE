package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.CartDetailService;
import com.fpt.duantn.shrared.dto.CRUD.CartDetailDto;
import com.fpt.duantn.ui.model.request.CartDetailDetailsRequestModel;
import com.fpt.duantn.ui.model.response.CartDetailRest;
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
@RequestMapping("/cartDetail")
public class CartDetailController {

    @Autowired
    CartDetailService cartDetailService;

    @GetMapping(path = "/{id}")
    public CartDetailRest getCartDetail(@PathVariable Long id) {
        CartDetailRest returnValue = new CartDetailRest();

        CartDetailDto cartDetailDto = cartDetailService.getCartDetailByCartDetailCode(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(cartDetailDto, CartDetailRest.class);

        return returnValue;
    }

    @PostMapping()
    public CartDetailRest createCartDetail(@RequestBody CartDetailDetailsRequestModel cartDetailDetails) throws Exception {
        CartDetailRest returnValue = new CartDetailRest();

        ModelMapper modelMapper = new ModelMapper();
        CartDetailDto cartDetailDto = modelMapper.map(cartDetailDetails, CartDetailDto.class);

        CartDetailDto createdUser = cartDetailService.createCartDetail(cartDetailDto);
        returnValue = modelMapper.map(createdUser, CartDetailRest.class);

        return returnValue;
    }

    @GetMapping()
    public List<CartDetailRest> getCartDetails(@RequestParam(value = "page", defaultValue = "0") int page,
                                                         @RequestParam(value = "limit", defaultValue = "2") int limit) {
        List<CartDetailRest> returnValue = new ArrayList<>();

        List<CartDetailDto> cartDetails = cartDetailService.getCartDetails(page, limit);

        for (CartDetailDto cartDetailDto : cartDetails) {
            CartDetailRest cartDetailModel = new CartDetailRest();
            BeanUtils.copyProperties(cartDetailDto, cartDetailModel);
            returnValue.add(cartDetailModel);
        }

        return returnValue;
    }


    @PutMapping(path = "/{id}")
    public CartDetailRest updateCartDetail(@PathVariable Long id, @RequestBody CartDetailDetailsRequestModel cartDetailDetails) {
        CartDetailRest returnValue = new CartDetailRest();

        CartDetailDto cartDetailDto = new CartDetailDto();
        cartDetailDto = new ModelMapper().map(cartDetailDetails, CartDetailDto.class);

        CartDetailDto updateCartDetail = cartDetailService.updateCartDetail(id, cartDetailDto);
        returnValue = new ModelMapper().map(updateCartDetail, CartDetailRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteCartDetail(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        cartDetailService.deleteCartDetail(id);

        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
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

}
