package com.fpt.duantn.ui.model.response;

import com.fpt.duantn.io.entity.BillEntity;
import com.fpt.duantn.io.entity.CartEntity;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.entity.ProductTypeEntity;
import com.fpt.duantn.io.entity.PromotionEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CartDetailRest {

    private ProductDetailEntity productDetail;

    private BillEntity bill;

    private CartEntity cart;

    private BigDecimal price;

    private Integer amount;

    private Date createDate;

    private Date updateDate;

    private Integer status;

}
