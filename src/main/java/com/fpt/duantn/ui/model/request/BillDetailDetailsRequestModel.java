package com.fpt.duantn.ui.model.request;


import com.fpt.duantn.io.entity.BillEntity;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class BillDetailDetailsRequestModel {

    private BillEntity bill;

    private ProductDetailEntity productDetail;

    private String productName;

    private String color;

    private String size;

    private Integer amount;

    private BigDecimal defaultPrice;

    private BigDecimal price;

    private Date createDate;

    private Date updateTime;

    private Integer status;
}
