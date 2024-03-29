package com.fpt.duantn.services;

import com.fpt.duantn.io.entity.BillEntity;
import com.fpt.duantn.shrared.dto.CRUD.BillDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BillService {

    BillDto createBill(BillDto bill);
    BillDto getBillById(Long billId);
    BillDto updateBill(Long billId, BillDto bill);
    void deleteBill(Long billId);
    List<BillDto> getBills(int page, int limit, String filter,int status);

    List<BillDto> getBills(int page, int limit, String filter, int status, Long userId);

    boolean existsById(Long aLong);

    Long count();

    List<BillEntity> findByPaymentTypeAndStatusAndCreateDateBefore(Integer paymentType, Integer status, LocalDateTime billCreateDate);

    <S extends BillEntity> S save(S entity);

    Long count(String filter,int status);

//    List<BillDto> getBillByBillName(String billName, int page, int limit);
Optional<BillEntity> findById(Long id);

    Long count(String filter, int status, Long userId);
}
