package com.fpt.duantn.shrared.dto.CRUD;

import com.fpt.duantn.io.entity.BillEntity;
import com.fpt.duantn.io.entity.ProductDetailEntity;
import com.fpt.duantn.io.entity.PromotionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class BillDetailDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private long id;

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
