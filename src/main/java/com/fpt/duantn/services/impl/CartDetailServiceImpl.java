package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.CartDetailServiceException;
import com.fpt.duantn.io.entity.CartDetailEntity;
import com.fpt.duantn.io.repository.CartDetailRepository;
import com.fpt.duantn.services.CartDetailService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.CartDetailDto;
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
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Autowired
    Utils utils;

    @Override
    public CartDetailDto createCartDetail(CartDetailDto cartDetail) {
        // Kiểm tra xem CartDetailCode đã tồn tại hay chưa
        if (cartDetailRepository.findCartDetailEntityById(cartDetail.getId()) != null) {
            throw new CartDetailServiceException("CartDetail with the same code already exists");
        }

        // Chuyển đổi CartDetailDto thành CartDetailEntity
        ModelMapper modelMapper = new ModelMapper();
        CartDetailEntity cartDetailEntity = modelMapper.map(cartDetail, CartDetailEntity.class);

        // Tạo một mã ngẫu nhiên cho CartDetailCode (tùy theo yêu cầu)
//        String publicCartDetailCode = utils.generateColorCode(8);
//        CartDetailEntity.setId(publicCartDetailCode);

        //them khoa ngoai
        cartDetailEntity.setCart(cartDetailEntity.getCart());
        cartDetailEntity.setBill(cartDetailEntity.getBill());
        cartDetailEntity.setProductDetail(cartDetailEntity.getProductDetail());

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        CartDetailEntity storedCartDetailDetails = cartDetailRepository.save(cartDetailEntity);

        // Chuyển đổi CartDetailEntity thành CartDetailDto
        CartDetailDto returnValue = modelMapper.map(storedCartDetailDetails, CartDetailDto.class);

        return returnValue;
    }



    @Override
    public CartDetailDto getCartDetailByCartDetailCode(Long cartDetailCode) {
        CartDetailDto returnValue = new CartDetailDto();
        CartDetailEntity cartDetailEntity = cartDetailRepository.findCartDetailEntityById(cartDetailCode);

        if (cartDetailEntity == null)
            throw new CartDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(cartDetailEntity, returnValue);

        return returnValue;
    }

    @Override
    public CartDetailDto updateCartDetail(Long cartDetailCode, CartDetailDto cartDetail) {
        CartDetailDto returnValue = new CartDetailDto();

        CartDetailEntity cartDetailEntity = cartDetailRepository.findCartDetailEntityById(cartDetailCode);

        if (cartDetailEntity == null)
            throw new CartDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());


        cartDetailEntity.setStatus(cartDetail.getStatus());
        cartDetailEntity.setUpdateDate(cartDetail.getUpdateDate());
        cartDetailEntity.setCreateDate(cartDetail.getCreateDate());
        cartDetailEntity.setBill(cartDetail.getBill());
        cartDetailEntity.setProductDetail(cartDetail.getProductDetail());
        cartDetailEntity.setAmount(cartDetail.getAmount());
        cartDetailEntity.setPrice(cartDetail.getPrice());
        cartDetailEntity.setCart(cartDetail.getCart());


        CartDetailEntity updatedCartDetails = cartDetailRepository.save(cartDetailEntity);
        returnValue = new ModelMapper().map(updatedCartDetails, CartDetailDto.class);

        return returnValue;
    }

    @Override
    public void deleteCartDetail(Long cartDetailId) {
        CartDetailEntity cartDetailEntity = cartDetailRepository.findCartDetailEntityById(cartDetailId);

        if (cartDetailEntity == null)
            throw new CartDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        cartDetailRepository.delete(cartDetailEntity);
    }

    @Override
    public List<CartDetailDto> getCartDetails(int page, int limit) {
        List<CartDetailDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<CartDetailEntity> cartDetailPage = cartDetailRepository.findAll(pageableRequest);
        List<CartDetailEntity> cartDetails = cartDetailPage.getContent();

        for (CartDetailEntity cartDetailEntity : cartDetails) {
            CartDetailDto cartDetailDto = new CartDetailDto();
            BeanUtils.copyProperties(cartDetailEntity, cartDetailDto);
            returnValue.add(cartDetailDto);
        }

        return returnValue;
    }

//    @Override
//    public List<CartDetailDto> getCartDetailByCartDetailName(String cartDetailName, int page, int limit) {
//        List<CartDetailDto> returnValue = new ArrayList<>();
//
//        if(page>0) page = page-1;
//
//        Pageable pageableRequest = PageRequest.of(page, limit);
//        Page<CartDetailEntity> cartDetailPage = cartDetailRepository.findByCartDetailContainingOrderByIdAsc(cartDetailName, pageableRequest);
//        List<CartDetailEntity> cartDetails = cartDetailPage.getContent();
//
//        for (CartDetailEntity cartDetailEntity : cartDetails) {
//            CartDetailDto cartDetailDto = new CartDetailDto();
//            BeanUtils.copyProperties(cartDetailEntity, cartDetailDto);
//            returnValue.add(cartDetailDto);
//        }
//
//        return returnValue;
//    }
}
