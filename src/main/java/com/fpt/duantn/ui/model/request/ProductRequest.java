package com.fpt.duantn.ui.model.request;

import com.fpt.duantn.io.entity.BrandEntity;
import com.fpt.duantn.io.entity.CategoryEntity;
import com.fpt.duantn.io.entity.MaterialEntity;
import com.fpt.duantn.io.entity.SizeEntity;
import com.fpt.duantn.io.entity.WaistbandEntity;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ProductRequest {

    private String productName;

    private CategoryEntity category;

    private BrandEntity brand;

    private String mainImage;

    private Long totalSold;


    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Integer status;

    private WaistbandEntity waistband;

    private MaterialEntity material;

}
