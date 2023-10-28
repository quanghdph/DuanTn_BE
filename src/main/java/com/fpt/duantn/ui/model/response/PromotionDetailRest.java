package com.fpt.duantn.ui.model.response;

import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.entity.ProductTypeEntity;
import com.fpt.duantn.io.entity.PromotionEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PromotionDetailRest {

    private PromotionEntity promotion;

    private ProductDetailEntity productDetail;

    private Integer amount;

    private Date endDate;

    private Date createDate;

    private Date updateDate;

    private Integer status;

}
