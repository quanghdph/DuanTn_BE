package com.fpt.duantn.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellOfRequest {
    private List<SellOfProductRequest> sanPhams;
    private Long idKhachHang;
    private Integer thanhToan;
    private Integer trangThaiTT;
    private String note;
}
