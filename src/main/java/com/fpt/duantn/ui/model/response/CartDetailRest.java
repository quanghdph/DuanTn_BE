package com.fpt.duantn.ui.model.response;

import com.fpt.duantn.io.entity.ProductDetailEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CartDetailRest {

    private Long id;

    private ProductDetailEntity productDetail;

    private Date createDate;

    private Date updateDate;

    private Integer status;

}
