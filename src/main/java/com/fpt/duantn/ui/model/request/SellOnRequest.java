package com.fpt.duantn.ui.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SellOnRequest {
    private List<SellOfProductRequest> sanPhams;
    private String phoneNumber;
    private String address;
    private String city;
    private String district;
    private String ward;
    private String note;
    private Integer paymentType;
}
