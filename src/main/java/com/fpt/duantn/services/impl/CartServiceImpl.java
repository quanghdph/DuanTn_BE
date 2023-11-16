package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.CartServiceException;
import com.fpt.duantn.io.entity.CartEntity;
import com.fpt.duantn.io.repository.CartRepository;
import com.fpt.duantn.services.CartService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.CartDto;
import com.fpt.duantn.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    Utils utils;

    @Override
    public CartDto createCart(CartDto cart) {
        // Kiểm tra xem CartCode đã tồn tại hay chưa
        if (cartRepository.findByCartCode(cart.getCartCode()) != null) {
            throw new CartServiceException("Cart with the same code already exists");
        }

        // Chuyển đổi CartDto thành CartEntity
        ModelMapper modelMapper = new ModelMapper();
        CartEntity cartEntity = modelMapper.map(cart, CartEntity.class);

        // Tạo một mã ngẫu nhiên cho CartCode (tùy theo yêu cầu)
        String publicCartCode = utils.generateColorCode(8);
        cartEntity.setCartCode(publicCartCode);

        //them khoa ngoai
        cartEntity.setEmployee(cart.getEmployee());
        cartEntity.setCustomer(cart.getCustomer());

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        CartEntity storedCartDetails = cartRepository.save(cartEntity);

        // Chuyển đổi CartEntity thành CartDto
        CartDto returnValue = modelMapper.map(storedCartDetails, CartDto.class);

        return returnValue;
    }



    @Override
    public CartDto getCartByCartCode(String cartCode) {
        CartDto returnValue = new CartDto();
        CartEntity cartEntity = cartRepository.findByCartCode(cartCode);

        if (cartEntity == null)
            throw new CartServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(cartEntity, returnValue);

        return returnValue;
    }

    @Override
    public CartDto updateCart(String cartCode, CartDto cart) {
        CartDto returnValue = new CartDto();

        CartEntity cartEntity = cartRepository.findByCartCode(cartCode);

        if (cartEntity == null)
            throw new CartServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        cartEntity.setStatus(cart.getStatus());
        cartEntity.setUpdateDate(cart.getUpdateDate());
        cartEntity.setCreateDate(cart.getCreateDate());
        cartEntity.setEmployee(cart.getEmployee());
        cartEntity.setCustomer(cart.getCustomer());


        CartEntity updatedCarts = cartRepository.save(cartEntity);
        returnValue = new ModelMapper().map(updatedCarts, CartDto.class);

        return returnValue;
    }

    @Override
    public void deleteCart(String cartCode) {
        CartEntity cartEntity = cartRepository.findByCartCode(cartCode);

        if (cartEntity == null)
            throw new CartServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        cartRepository.delete(cartEntity);
    }

    @Override
    public List<CartDto> getCarts(int page, int limit, String filter) {
        List<CartDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<CartEntity> cartPage = cartRepository.filter(filter, pageableRequest);
        List<CartEntity> carts = cartPage.getContent();

        for (CartEntity cartEntity : carts) {
            CartDto cartDto = new CartDto();
            BeanUtils.copyProperties(cartEntity, cartDto);
            returnValue.add(cartDto);
        }

        return returnValue;
    }

    @Override
    public Long count(String filter) {

        Long total = cartRepository.count(filter);
        return total;
    }

    @Override
    public List<CartDto> getCartByCartName(String cartName, int page, int limit) {
        List<CartDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<CartEntity> cartPage = cartRepository.findByCartCodeContainingOrderByIdAsc(cartName, pageableRequest);
        List<CartEntity> carts = cartPage.getContent();

        for (CartEntity cartEntity : carts) {
            CartDto cartDto = new CartDto();
            BeanUtils.copyProperties(cartEntity, cartDto);
            returnValue.add(cartDto);
        }

        return returnValue;
    }
}
