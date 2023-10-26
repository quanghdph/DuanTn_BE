package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CartDto;

import java.util.List;

public interface CartService {

    CartDto createCart(CartDto cart);
    CartDto getCartByCartCode(String cartCode);
    CartDto updateCart(String cartCode, CartDto cart);
    void deleteCart(String cartCode);
    List<CartDto> getCarts(int page, int limit);
    List<CartDto> getCartByCartName(String cartName, int page, int limit);


}
