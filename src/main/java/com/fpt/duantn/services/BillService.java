package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.BillDto;

import java.util.List;

public interface BillService {

    BillDto createBill(BillDto bill);
    BillDto getBillByBillCode(String billCode);
    BillDto updateBill(String billCode, BillDto bill);
    void deleteBill(String billCode);
    List<BillDto> getBills(int page, int limit);
    List<BillDto> getBillByBillName(String billName, int page, int limit);


}
