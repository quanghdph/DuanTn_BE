package com.fpt.duantn.services;

import com.fpt.duantn.io.entity.BillEntity;
import com.fpt.duantn.shrared.dto.CRUD.BillDto;

import java.util.List;

public interface BillService {

    BillDto createBill(BillDto bill);
    BillDto getBillById(Long billId);
    BillDto updateBill(Long billId, BillDto bill);
    void deleteBill(Long billId);
    List<BillDto> getBills(int page, int limit, String filter);

    boolean existsById(Long aLong);

    Long count();

    <S extends BillEntity> S save(S entity);

    Long count(String filter);

//    List<BillDto> getBillByBillName(String billName, int page, int limit);


}
