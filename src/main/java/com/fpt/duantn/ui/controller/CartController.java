package com.fpt.duantn.ui.controller;

import com.fpt.duantn.services.CartService;
import com.fpt.duantn.shrared.dto.CRUD.CartDto;
import com.fpt.duantn.ui.model.request.CartRequest;
import com.fpt.duantn.ui.model.response.CartRest;
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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping(path = "/{id}")
    public CartRest getCart(@PathVariable Long id) {
        CartRest returnValue = new CartRest();

        CartDto cartDto = cartService.getCartById(id);
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(cartDto, CartRest.class);

        return returnValue;
    }

    @PostMapping()
    public CartRest createCart(@RequestBody CartRequest cartDetails) throws Exception {
        CartRest returnValue = new CartRest();

        ModelMapper modelMapper = new ModelMapper();
        CartDto cartDto = modelMapper.map(cartDetails, CartDto.class);

        cartDto.setEmployee(cartDetails.getEmployee());
        cartDto.setCustomer(cartDetails.getCustomer());

        CartDto createdUser = cartService.createCart(cartDto);
        returnValue = modelMapper.map(createdUser, CartRest.class);

        return returnValue;
    }



    @PutMapping(path = "/{id}")
    public CartRest updateCart(@PathVariable Long id, @RequestBody CartRequest cartDetails) {
        CartRest returnValue = new CartRest();

        CartDto cartDto = new CartDto();
        cartDto = new ModelMapper().map(cartDetails, CartDto.class);

        cartDto.setEmployee(cartDetails.getEmployee());
        cartDto.setCustomer(cartDetails.getCustomer());

        CartDto updateCart = cartService.updateCart(id, cartDto);
        returnValue = new ModelMapper().map(updateCart, CartRest.class);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteCart(@PathVariable Long id) {
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());

        try {
            cartService.deleteCart(id);
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
            returnValue.setOperationMessage("Xoa Thanh Cong.");
        }catch (DataIntegrityViolationException exception){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Gio Hang: Gio Hang có tham chiếu đến khoá ngoại.");
        }catch (Exception e){
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
            returnValue.setOperationMessage("Lỗi khi xóa Gio Hang: " + e.getMessage());
        }return returnValue;
    }



    @GetMapping("/search")
    public List<CartRest> searchCarts(@RequestParam(value = "cartName") String cartName,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "limit", defaultValue = "5") int limit) {
        List<CartRest> returnValue = new ArrayList<>();

        List<CartDto> carts = cartService.getCartByCartName(cartName, page, limit);

        for (CartDto cartDto : carts) {
            CartRest cartModel = new CartRest();
            BeanUtils.copyProperties(cartDto, cartModel);
            returnValue.add(cartModel);
        }

        return returnValue;
    }

    @GetMapping()
    public PaginationRest getCarts(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "5") int limit,
                                   @RequestParam(value = "filter", defaultValue = "") String filter) {
        List<CartRest> returnValue = new ArrayList<>();

        List<CartDto> carts = cartService.getCarts(page, limit, filter);

        for (CartDto cartDto : carts) {
            CartRest cartModel = new CartRest();
            BeanUtils.copyProperties(cartDto, cartModel);
            returnValue.add(cartModel);
        }
        PaginationRest paginationRest = new PaginationRest();
        paginationRest.setListCart(returnValue);
        paginationRest.setTotal(cartService.count(filter));

        return paginationRest;
    }


}
