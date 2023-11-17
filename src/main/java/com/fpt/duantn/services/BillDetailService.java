package com.fpt.duantn.services;

import com.fpt.duantn.shrared.dto.CRUD.BillDetailDto;

import java.util.List;

public interface BillDetailService {

    BillDetailDto createBillDetail(BillDetailDto billDetail);
    BillDetailDto getBillDetailById(Long billDetailId);
    BillDetailDto updateBillDetail(Long billDetailId, BillDetailDto billDetail);
    void deleteBillDetail(Long billDetailId);
    List<BillDetailDto> getBillDetails(int page, int limit, String filter);
    Long count(String filter);
//    List<BillDetailDto> getBillDetailByBillDetailName(String billDetailName, int page, int limit);


}
