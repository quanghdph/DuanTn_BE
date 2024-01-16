package com.fpt.duantn.services;

import com.fpt.duantn.io.entity.BillDetailEntity;
import com.fpt.duantn.shrared.dto.CRUD.BillDetailDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BillDetailService {

    BillDetailDto createBillDetail(BillDetailDto billDetail);

    List<BillDetailEntity> findByBillIdAndStatus(Long id, Integer status);

    Optional<Double> sumMoneyByBillIdAndType(Long billId, Integer status);

    Optional<Long> sumQuantityByBillIdAndType(Long billId, Integer status);

    <S extends BillDetailEntity> List<S> saveAll(Iterable<S> entities);

    <S extends BillDetailEntity> S save(S entity);

    BillDetailDto getBillDetailById(Long billDetailId);
    BillDetailDto updateBillDetail(Long billDetailId, BillDetailDto billDetail);
    void deleteBillDetail(Long billDetailId);
    List<BillDetailDto> getBillDetails(int page, int limit, String filter);
    Long count(String filter);
//    List<BillDetailDto> getBillDetailByBillDetailName(String billDetailName, int page, int limit);
    List<BillDetailEntity> findByBillId(Long id);

}
