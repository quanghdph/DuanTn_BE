package com.fpt.duantn.shrared.dto.CRUD;

import com.fpt.duantn.io.entity.BrandEntity;
import com.fpt.duantn.io.entity.CategoryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Getter
@Setter
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private Long id;

    private String productName;

    private CategoryEntity category;

    private BrandEntity brand;

    private String mainImage;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Integer status;

    private String productCode;

    private Long quantity;

    private BigDecimal price;


}
