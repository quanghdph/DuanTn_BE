package com.fpt.duantn.shrared.dto.CRUD;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 6835192601898364280L;

    private Long id;
    private String categoryName;
    private String categoryCode;
    private Integer status;
    private Date createDate;
    private Date updateDate;
}
