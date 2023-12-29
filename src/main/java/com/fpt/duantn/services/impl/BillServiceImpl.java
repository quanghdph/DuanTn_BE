package com.fpt.duantn.services.impl;

import com.fpt.duantn.exceptions.BillServiceException;
import com.fpt.duantn.io.entity.BillEntity;
import com.fpt.duantn.io.repository.BillRepository;
import com.fpt.duantn.services.BillService;
import com.fpt.duantn.shrared.Utils;
import com.fpt.duantn.shrared.dto.CRUD.BillDto;
import com.fpt.duantn.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Autowired
    Utils utils;

    @Override
    public BillDto createBill(BillDto bill) {
        // Kiểm tra xem BillCode đã tồn tại hay chưa
//        if (billRepository.findByBillCode(bill.getBillCode()) != null) {
//            throw new BillServiceException("Bill with the same code already exists");
//        }

        // Chuyển đổi BillDto thành BillEntity
        ModelMapper modelMapper = new ModelMapper();
        BillEntity billEntity = modelMapper.map(bill, BillEntity.class);

        // Tạo một mã ngẫu nhiên cho BillCode (tùy theo yêu cầu)
//        String publicBillCode = utils.generateColorCode(8);
//        billEntity.setBillCode(publicBillCode);

        //them khoa ngoai
        billEntity.setCustomer(bill.getCustomer());
        billEntity.setEmployee(bill.getEmployee());

        // Lưu trữ thông tin màu vào cơ sở dữ liệu
        BillEntity storedBillDetails = billRepository.save(billEntity);

        // Chuyển đổi BillEntity thành BillDto
        BillDto returnValue = modelMapper.map(storedBillDetails, BillDto.class);

        return returnValue;
    }



    @Override
    public BillDto getBillById(Long billId) {
        BillDto returnValue = new BillDto();
        BillEntity billEntity = billRepository.findBillEntityById(billId);

        if (billEntity == null)
            throw new BillServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        BeanUtils.copyProperties(billEntity, returnValue);

        return returnValue;
    }

    @Override
    public BillDto updateBill(Long billId, BillDto bill) {
        BillDto returnValue = new BillDto();

        BillEntity billEntity = billRepository.findBillEntityById(billId);

        if (billEntity == null)
            throw new BillServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        billEntity.setCustomer(bill.getCustomer());
        billEntity.setEmployee(bill.getEmployee());
        billEntity.setCreateDate(bill.getCreateDate());
        billEntity.setUpdateDate(bill.getUpdateDate());
        billEntity.setPaymentDate(bill.getPaymentDate());
        billEntity.setDeliveryDate(bill.getDeliveryDate());
        billEntity.setAddress(bill.getAddress());
        billEntity.setPhoneNumber(bill.getPhoneNumber());
        billEntity.setTransportFee(bill.getTransportFee());
        billEntity.setNote(bill.getNote());
        billEntity.setStatus(bill.getStatus());

        BillEntity updatedBills = billRepository.save(billEntity);
        returnValue = new ModelMapper().map(updatedBills, BillDto.class);

        return returnValue;
    }

    @Override
    public void deleteBill(Long billId) {
        BillEntity billEntity = billRepository.findBillEntityById(billId);

        if (billEntity == null)
            throw new BillServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        billRepository.delete(billEntity);
    }

    @Override
    public List<BillDto> getBills(int page, int limit, String filter) {
        List<BillDto> returnValue = new ArrayList<>();

        if(page>0) page = page-1;

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<BillEntity> billPage = billRepository.filter(filter, pageableRequest);
        List<BillEntity> bills = billPage.getContent();

        for (BillEntity billEntity : bills) {
            BillDto billDto = new BillDto();
            BeanUtils.copyProperties(billEntity, billDto);
            returnValue.add(billDto);
        }

        return returnValue;
    }


    @Override
    public Long count() {

        return this.billRepository.count();
    }

    @Override
    public Long count(String filter) {
        return this.billRepository.count(filter);
    }

//    @Override
//    public List<BillDto> getBillByBillName(String billName, int page, int limit) {
//        List<BillDto> returnValue = new ArrayList<>();
//
//        if(page>0) page = page-1;
//
//        Pageable pageableRequest = PageRequest.of(page, limit);
//        Page<BillEntity> billPage = billRepository.findByCustomerNameContainingOrderByIdAsc(billName, pageableRequest);
//        List<BillEntity> bills = billPage.getContent();
//
//        for (BillEntity billEntity : bills) {
//            BillDto billDto = new BillDto();
//            BeanUtils.copyProperties(billEntity, billDto);
//            returnValue.add(billDto);
//        }
//
//        return returnValue;
//    }
}
