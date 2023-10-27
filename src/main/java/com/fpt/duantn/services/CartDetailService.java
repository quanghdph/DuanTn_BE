package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CartDetailDto;

import java.util.List;

public interface CartDetailService {

    CartDetailDto createCartDetail(CartDetailDto cartDetail);
    CartDetailDto getCartDetailByCartDetailCode(Long cartDetailCode);
    CartDetailDto updateCartDetail(Long cartDetailCode, CartDetailDto cartDetail);
    void deleteCartDetail(Long cartDetailCode);
    List<CartDetailDto> getCartDetails(int page, int limit);
//    List<CartDetailDto> getCartDetailByCartDetailName(String cartDetailName, int page, int limit);


}
