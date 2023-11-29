package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CartDto;

import java.util.List;

public interface CartService {

    CartDto createCart(CartDto cart);
    CartDto getCartById(Long cartId);
    CartDto updateCart(Long cartId, CartDto cart);
    void deleteCart(Long cartId);
    List<CartDto> getCarts(int page, int limit, String filter);
    Long count(String filter);
//    List<CartDto> getCartByCartName(String cartName, int page, int limit);


}
