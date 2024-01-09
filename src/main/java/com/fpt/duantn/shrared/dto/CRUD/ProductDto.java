package com.fpt.duantn.shrared.dto.CRUD;

import com.fpt.duantn.io.entity.BrandEntity;
import com.fpt.duantn.io.entity.CategoryEntity;
import com.fpt.duantn.io.entity.MaterialEntity;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;


@Getter
@Setter
public class ProductDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private Long id;

    private String productName;

    private CategoryEntity category;

    private BrandEntity brand;

    private Blob mainImage;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Integer status;


    private String productCode;

    private MaterialEntity material;




}
