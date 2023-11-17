package com.fpt.duantn.shrared.dto.CRUD;

import com.fpt.duantn.io.entity.ColorEntity;
import com.fpt.duantn.io.entity.MaterialEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.entity.SizeEntity;
import com.fpt.duantn.io.entity.WaistbandEntity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
public class ProductDetailDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private long id;

    private ProductEntity product;

    private ColorEntity color;

    private SizeEntity size;

    private BigDecimal defaultPrice;

    private BigDecimal price;

    private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Integer status;

}
