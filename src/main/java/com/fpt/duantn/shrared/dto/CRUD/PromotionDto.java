package com.fpt.duantn.shrared.dto.CRUD;

import com.fpt.duantn.io.entity.ProductTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PromotionDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private long id;

    private String promotionCode;
    private Integer amount;

    private BigDecimal discountLevel;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    private Integer status;

}
