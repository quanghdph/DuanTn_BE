package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.CartDetailDto;

import java.util.List;

public interface CartDetailService {

    CartDetailDto createCartDetail(CartDetailDto cartDetail);
    CartDetailDto getCartDetailById(Long cartDetailId);
    CartDetailDto updateCartDetail(Long cartDetailId, CartDetailDto cartDetail);
    void deleteCartDetail(Long cartDetailId);
    List<CartDetailDto> getCartDetails(int page, int limit, String filter);
    Long count(String filter);
//    List<CartDetailDto> getCartDetailByCartDetailName(String cartDetailName, int page, int limit);


}
