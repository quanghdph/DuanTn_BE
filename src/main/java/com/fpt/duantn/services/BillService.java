package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.BillDto;

import java.util.List;

public interface BillService {

    BillDto createBill(BillDto bill);
    BillDto getBillById(Long billId);
    BillDto updateBill(Long billId, BillDto bill);
    void deleteBill(Long billId);
    List<BillDto> getBills(int page, int limit, String filter);
    Long count(String filter);

    List<BillDto> getBillByBillName(String billName, int page, int limit);


}
