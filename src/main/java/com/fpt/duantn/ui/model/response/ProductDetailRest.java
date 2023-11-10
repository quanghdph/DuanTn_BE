package com.fpt.duantn.ui.model.response;

import com.fpt.duantn.io.entity.CollarEntity;
import com.fpt.duantn.io.entity.ColorEntity;
import com.fpt.duantn.io.entity.MaterialEntity;
import com.fpt.duantn.io.entity.PatternEntity;
import com.fpt.duantn.io.entity.ProductEntity;
import com.fpt.duantn.io.entity.SizeEntity;
import com.fpt.duantn.io.entity.WaistbandEntity;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProductDetailRest {

    private Long id;

    private ProductEntity product;

    private ColorEntity color;

    private SizeEntity size;

    private MaterialEntity material;

    private PatternEntity pattern;

    private CollarEntity collar;

    private WaistbandEntity waistband;

    private BigDecimal defaultPrice;

    private BigDecimal price;

    private Integer amount;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Integer status;

    private String productDetailCode;

}
