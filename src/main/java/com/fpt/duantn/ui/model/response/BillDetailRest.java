package com.fpt.duantn.ui.model.response;



import com.fpt.duantn.io.entity.BillEntity;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BillDetailRest {

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
