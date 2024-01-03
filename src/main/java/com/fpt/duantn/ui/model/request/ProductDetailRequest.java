package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.ColorEntity;
import com.fpt.duantn.io.entity.MaterialEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.entity.SizeEntity;
import com.fpt.duantn.io.entity.WaistbandEntity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProductDetailRequest {

    private ProductEntity product;

    private ColorEntity color;

    private SizeEntity size;

    private BigDecimal price;

    private Integer quantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Integer status;
    List<ProductEntity> productEntities;
}
