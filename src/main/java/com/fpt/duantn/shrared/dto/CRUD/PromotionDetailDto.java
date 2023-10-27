package com.fpt.duantn.shrared.dto.CRUD;

import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.entity.ProductTypeEntity;
import com.fpt.duantn.io.entity.PromotionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class PromotionDetailDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private long id;

    private PromotionEntity promotion;

    private ProductDetailEntity productDetail;

    private Integer amount;

    private Date endDate;

    private Date createDate;

    private Date updateDate;

    private Integer status;
}
