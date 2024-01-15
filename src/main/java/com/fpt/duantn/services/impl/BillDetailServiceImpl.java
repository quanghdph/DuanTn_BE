package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.BillDetailServiceException;
import com.fpt.duantn.io.entity.BillDetailEntity;
import com.fpt.duantn.io.repository.BillDetailRepository;
import com.fpt.duantn.services.BillDetailService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.BillDetailDto;
import com.fpt.duantn.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BillDetailServiceImpl implements BillDetailService {

    @Autowired
    BillDetailRepository billDetailRepository;

    @Autowired
    Utils utils;

    @Override
    public BillDetailDto createBillDetail(BillDetailDto billDetail) {
        // Kiểm tra xem BillDetailCode đã tồn tại hay chưa
        if (billDetailRepository.findBillDetailEntityById(billDetail.getId()) != null) {
            throw new BillDetailServiceException("BillDetail with the same code already exists");
        }

        // Chuyển đổi BillDetailDto thành BillDetailEntity
        ModelMapper modelMapper = new ModelMapper();
        BillDetailEntity billDetailEntity = modelMapper.map(billDetail, BillDetailEntity.class);

        // Tạo một mã ngẫu nhiên cho BillDetailCode (tùy theo yêu cầu)
//        String publicBillDetailCode = utils.generateColorCode(8);
//        BillDetailEntity.setId(publicBillDetailCode);

        //them khoa ngoai
        billDetailEntity.setProductDetail(billDetail.getProductDetail());
        billDetailEntity.setBill(billDetail.getBill());

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        BillDetailEntity storedBillDetailDetails = billDetailRepository.save(billDetailEntity);

        // Chuyển đổi BillDetailEntity thành BillDetailDto
        BillDetailDto returnValue = modelMapper.map(storedBillDetailDetails, BillDetailDto.class);

        return returnValue;
    }

    @Override
    public List<BillDetailEntity> findByBillIdAndStatus(Long id, Integer status) {
        return billDetailRepository.findByBillIdAndStatus(id, status);
    }

    @Override
    public Optional<Double> sumMoneyByBillIdAndType(Long billId, Integer status) {
        return billDetailRepository.sumMoneyByBillIdAndType(billId, status);
    }

    @Override
    public Optional<Long> sumQuantityByBillIdAndType(Long billId, Integer status) {
        return billDetailRepository.sumQuantityByBillIdAndType(billId, status);
    }

    @Override
    public <S extends BillDetailEntity> List<S> saveAll(Iterable<S> entities) {
        return billDetailRepository.saveAll(entities);
    }

    @Override
    public <S extends BillDetailEntity> S save(S entity) {
        return billDetailRepository.save(entity);
    }

    @Override
    public BillDetailDto getBillDetailById(Long billDetailId) {
        BillDetailDto returnValue = new BillDetailDto();
        BillDetailEntity billDetailEntity = billDetailRepository.findBillDetailEntityById(billDetailId);

        if (billDetailEntity == null)
            throw new BillDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(billDetailEntity, returnValue);

        return returnValue;
    }

    @Override
    public BillDetailDto updateBillDetail(Long billDetailId, BillDetailDto billDetail) {
        BillDetailDto returnValue = new BillDetailDto();

        BillDetailEntity billDetailEntity = billDetailRepository.findBillDetailEntityById(billDetailId);

        if (billDetailEntity == null)
            throw new BillDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        billDetailEntity.setStatus(billDetail.getStatus());
        billDetailEntity.setCreateDate(billDetail.getCreateDate());
        billDetailEntity.setProductDetail(billDetail.getProductDetail());
        billDetailEntity.setPrice(billDetail.getPrice());
        billDetailEntity.setBill(billDetail.getBill());
        billDetailEntity.setDefaultPrice(billDetail.getDefaultPrice());
        billDetailEntity.setUpdateTime(billDetail.getUpdateTime());


        BillDetailEntity updatedBillDetails = billDetailRepository.save(billDetailEntity);
        returnValue = new ModelMapper().map(updatedBillDetails, BillDetailDto.class);

        return returnValue;
    }

    @Override
    public void deleteBillDetail(Long billDetailId) {
        BillDetailEntity billDetailEntity = billDetailRepository.findBillDetailEntityById(billDetailId);

        if (billDetailEntity == null)
            throw new BillDetailServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        billDetailRepository.delete(billDetailEntity);
    }

    @Override
    public List<BillDetailDto> getBillDetails(int page, int limit, String filter) {
        List<BillDetailDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<BillDetailEntity> billDetailPage = billDetailRepository.filter(filter, pageableRequest);
        List<BillDetailEntity> billDetails = billDetailPage.getContent();

        for (BillDetailEntity billDetailEntity : billDetails) {
            BillDetailDto billDetailDto = new BillDetailDto();
            BeanUtils.copyProperties(billDetailEntity, billDetailDto);
            returnValue.add(billDetailDto);
        }

        return returnValue;
    }

    @Override
    public Long count(String filter) {

        Long total = billDetailRepository.count(filter);
        return total;
    }

//    @Override
//    public List<BillDetailDto> getBillDetailByBillDetailName(String BillDetailName, int page, int limit) {
//        List<BillDetailDto> returnValue = new ArrayList<>();
//
//        if(page>0) page = page-1;
//
//        Pageable pageableRequest = PageRequest.of(page, limit);
//        Page<BillDetailEntity> BillDetailPage = BillDetailRepository.findByBillDetailContainingOrderByIdAsc(BillDetailName, pageableRequest);
//        List<BillDetailEntity> BillDetails = BillDetailPage.getContent();
//
//        for (BillDetailEntity BillDetailEntity : BillDetails) {
//            BillDetailDto BillDetailDto = new BillDetailDto();
//            BeanUtils.copyProperties(BillDetailEntity, BillDetailDto);
//            returnValue.add(BillDetailDto);
//        }
//
//        return returnValue;
//    }


    @Override
    public List<BillDetailEntity> findByBillId(Long id) {
        return billDetailRepository.findByBillId(id);
    }
}
