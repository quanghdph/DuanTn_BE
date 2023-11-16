package com.fpt.duantn.shrared.dto.CRUD;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BrandDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private long id;

    private String brandCode;

    private String brandName;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    private Integer status;
}
